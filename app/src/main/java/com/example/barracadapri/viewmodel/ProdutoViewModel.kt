package com.example.barracadapri.viewmodel

import androidx.lifecycle.ViewModel
import com.example.barracadapri.model.Produto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class ProdutoViewModel : ViewModel() {
    private val _produtos = MutableStateFlow<List<Produto>>(emptyList())
    val produtos: StateFlow<List<Produto>> get() = _produtos

    fun adicionarProduto(produto: Produto) {
        _produtos.value = _produtos.value + produto
    }

    fun listarProdutos(): List<Produto> {
        return _produtos.value
    }

    fun atualizarProduto(produto: Produto) {
        _produtos.value = _produtos.value.map { if (it.nome == produto.nome) produto else it }
    }

    fun excluirProduto(produto: Produto) {
        _produtos.value = _produtos.value.filter { it.nome != produto.nome }
    }

    fun getProdutoById(produtoId: Long): Produto? {
        return _produtos.value.find { it.id == produtoId }
    }

    fun atualizarEstoque(produtoId: Long, quantidadeVendida: Int) {
        val produto = _produtos.value.find { it.id == produtoId }
        produto?.let {
            val estoqueAtual = it.quantidade.toIntOrNull() ?: 0
            if (quantidadeVendida > estoqueAtual) {
                return
            }

            val novaQuantidade = (estoqueAtual - quantidadeVendida).coerceAtLeast(0).toString()
            val produtoAtualizado = it.copy(quantidade = novaQuantidade)
            _produtos.value = _produtos.value.map { p ->
                if (p.id == produtoId) produtoAtualizado else p
            }
        }
    }

}