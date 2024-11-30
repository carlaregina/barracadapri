package com.example.barracadapri.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.barracadapri.model.Produto


@Composable
fun ConfirmarVendaScreen(
    produtosSelecionados: Map<Produto, Int>,
    onFinalizeVenda: () -> Unit,
    onNavigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Confirmar Venda",
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(produtosSelecionados.entries.toList()) { (produto, quantidade) ->
                val total = (produto.preco.toDoubleOrNull() ?: 0.0) * quantidade

                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Produto: ${produto.nome}")
                    Text("Quantidade: $quantidade")
                    Text("Total: R$ %.2f".format(total))
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onNavigateBack) {
                Text("Voltar")
            }
            Button(
                onClick = {
                    try {
                        onFinalizeVenda()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                },
                enabled = produtosSelecionados.isNotEmpty()
            ) {
                Text("Finalizar Venda")
            }
        }
    }
}