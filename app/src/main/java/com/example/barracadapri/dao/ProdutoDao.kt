package com.example.barracadapri.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.example.barracadapri.model.Produto

@Dao
interface ProdutoDao {
    @Insert
    suspend fun inserir(produto: Produto)

    @Query("SELECT * FROM produtos")
    suspend fun listarTodos(): List<Produto>

    @Update
    suspend fun atualizar(produto: Produto)

    @Delete
    suspend fun deletar(produto: Produto)
}