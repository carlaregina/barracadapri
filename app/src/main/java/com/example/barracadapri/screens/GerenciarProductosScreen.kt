package com.example.barracadapri.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.barracadapri.model.Produto


import com.example.barracadapri.viewmodel.ProdutoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GerenciarProdutosScreen(
    viewModel: ProdutoViewModel,
    onEditProduto: (
        Produto
    ) -> Unit,
    onNavigateBack: () -> Unit
) {
    val produtosState = viewModel.produtos.collectAsState()
    val produtos = produtosState.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("voltar") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    )

    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(text = "Gerenciar Produtos", style = androidx.compose.material3.MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            if (produtos.isEmpty()) {
                Text(text = "Nenhum produto cadastrado.")
            } else {
                LazyColumn {
                    items(produtos) { produto ->
                        ProdutoCard(
                            produto = produto,
                            onDelete = { viewModel.excluirProduto(it) },
                            onEdit = onEditProduto
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProdutoCard(
    produto: Produto,
    onDelete: (Produto) -> Unit,
    onEdit: (Produto) -> Unit,

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
            Text("Quantidade: ${produto.quantidade}")

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { onEdit(produto) }) {
                    Text("Editar")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { onDelete(produto) }) {
                    Text("Excluir")
                }
            }
        }
    }
}