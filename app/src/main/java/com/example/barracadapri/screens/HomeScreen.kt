package com.example.barracadapri.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onNavigateToAdicionarProduto: () -> Unit,
    onNavigateToGerenciarProduto: () -> Unit,
    onNavigateToVendas: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Barraca Da Pri",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = onNavigateToAdicionarProduto,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar Produto")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onNavigateToGerenciarProduto,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Gerenciar Produtos")
        }
        Button(
            onClick = onNavigateToVendas,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Realizar Venda")
        }
    }
}