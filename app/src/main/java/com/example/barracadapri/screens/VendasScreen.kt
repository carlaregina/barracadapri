package com.example.barracadapri.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.barracadapri.model.Produto

import com.example.barracadapri.viewmodel.ProdutoViewModel

@Composable
fun VendasScreen(
    viewModel: ProdutoViewModel,
    onNavigateToConfirmarVenda: (Map<Produto, Int>) -> Unit
) {
    val produtos = viewModel.produtos.collectAsState().value
    val produtosSelecionados = remember { mutableStateMapOf<Produto, Int>() }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Vendas",
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (produtos.isEmpty()) {
            Text("Nenhum produto disponível para venda.")
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(produtos) { produto ->
                    ProdutoVendaCard(
                        produto = produto,
                        onAdd = { quantidade ->
                            val novaQuantidade = produtosSelecionados.getOrDefault(produto, 0) + quantidade
                            produtosSelecionados[produto] = novaQuantidade
                            println("Atualizado: ${produto.nome}, Nova Quantidade: $novaQuantidade")
                        },
                        onRemove = { quantidade ->
                            val novaQuantidade = produtosSelecionados.getOrDefault(produto, 0) - quantidade
                            if (novaQuantidade > 0) {
                                produtosSelecionados[produto] = novaQuantidade
                            } else {
                                produtosSelecionados.remove(produto)
                            }
                            produtosSelecionados.entries.forEach {
                                println("Exibindo: Produto: ${it.key.nome}, Quantidade: ${it.value}")
                            }
                        }

                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onNavigateToConfirmarVenda(produtosSelecionados) },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Confirmar Venda")
            }
        }
    }
}

@Composable
fun ProdutoVendaCard(
    produto: Produto,
    onAdd: (Int) -> Unit,
    onRemove: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Nome: ${produto.nome}")
            Text("Preço: R$ ${produto.preco}")
            Text("Estoque: ${produto.quantidade}")

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { onRemove(1) }) {
                    Text("-")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { onAdd(1) }) {
                    Text("+")
                }
            }
        }
    }
}