package com.example.barracadapri


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

import com.example.barracadapri.ui.theme.BarracadapriTheme
import com.example.barracadapri.viewmodel.ProdutoViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val produtoViewModel: ProdutoViewModel = viewModel()
            BarracaDaPriApp(produtoViewModel)
        }
    }
}

@Composable
fun BarracaDaPriApp(viewModel: ProdutoViewModel) {
    BarracadapriTheme {
        val navController = androidx.navigation.compose.rememberNavController()
        NavigationComponent(navController, viewModel)
    }
}