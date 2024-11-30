package com.example.barracadapri.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.barracadapri.R
import com.example.barracadapri.model.Produto


class ProdutoAdapter(private val produtos: List<Produto>) :
    RecyclerView.Adapter<ProdutoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNome: TextView = view.findViewById(R.id.txtNomeProduto)
        val txtPreco: TextView = view.findViewById(R.id.txtPrecoProduto)
        val txtQuantidade: TextView = view.findViewById(R.id.txtQuantidadeProduto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produto, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.txtNome.text = produto.nome
        holder.txtPreco.text = "R$ ${produto.preco}"
        holder.txtQuantidade.text = "Qtd: ${produto.quantidade}"
    }

    override fun getItemCount() = produtos.size
}