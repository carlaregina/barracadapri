package com.example.barracadapri.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.barracadapri.model.Produto

@Composable
fun EditarProdutosScreen(
    produto: Produto,
    onSave: (Produto) -> Unit,
    onNavigateBack: () -> Unit
) {
    var nome by remember { mutableStateOf(produto.nome) }
    var preco by remember { mutableStateOf(produto.preco) }
    var quantidade by remember { mutableStateOf(produto.quantidade) }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome do Produto") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = preco,
                onValueChange = { preco = it },
                label = { Text("Preço do Produto") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = quantidade,
                onValueChange = { quantidade = it },
                label = { Text("Quantidade") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    onSave(Produto(id = produto.id, nome,
                        (preco), (quantidade)
                    ))
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salvar Alterações")
            }
        }
    }
}