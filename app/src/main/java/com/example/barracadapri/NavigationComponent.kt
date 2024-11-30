package com.example.barracadapri

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.barracadapri.model.Produto
import com.example.barracadapri.screens.*
import com.example.barracadapri.viewmodel.ProdutoViewModel

@Composable
fun NavigationComponent(
    navController: androidx.navigation.NavHostController,
    viewModel: ProdutoViewModel
) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onNavigateToAdicionarProduto = { navController.navigate("add_product") },
                onNavigateToGerenciarProduto = { navController.navigate("manage_products") },
                onNavigateToVendas = { navController.navigate("vendas") } // Botão para navegação à tela de vendas
            )
        }
        composable("add_product") {
            AdicionarProdutoScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable("manage_products") {
            GerenciarProdutosScreen(
                viewModel = viewModel,
                onEditProduto = { produtoId ->
                    navController.navigate("edit_product/$produtoId")
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(
            route = "edit_product/{produtoId}",
            arguments = listOf(
                navArgument("produtoId") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val produtoId = backStackEntry.arguments?.getLong("produtoId") ?: -1L
            val produto = remember(produtoId) { viewModel.getProdutoById(produtoId) }

            if (produto != null) {
                EditarProdutosScreen(
                    produto = produto,
                    onSave = { produtoEditado ->
                        viewModel.atualizarProduto(produtoEditado)
                        navController.popBackStack()
                    },
                    onNavigateBack = { navController.popBackStack() }
                )
            } else {
                Text("Produto não encontrado.")
            }
        }
        composable("vendas") {
            VendasScreen(
                viewModel = viewModel,
                onNavigateToConfirmarVenda = { produtosSelecionados ->
                    produtosSelecionados.forEach { (produto, quantidade) ->
                        println("Produto: ${produto.nome}, Quantidade: $quantidade")
                    }
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "produtosSelecionados",
                        produtosSelecionados.toMap() // Converter para Map antes de salvar
                    )
                    navController.navigate("confirmar_venda")
                }
            )
        }

        composable("confirmar_venda") {
            val produtosSelecionados = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<Map<Produto, Int>>("produtosSelecionados")
                ?: emptyMap()
            ConfirmarVendaScreen(
                produtosSelecionados = produtosSelecionados,
                onFinalizeVenda = {
                    try {
                        produtosSelecionados.forEach { (produto, quantidade) ->
                            viewModel.atualizarEstoque(produto.id, quantidade)
                        }
                        navController.popBackStack("vendas", inclusive = true)
                    } catch (e: Exception) {
                        e.printStackTrace() // Log para depuração
                    }
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}