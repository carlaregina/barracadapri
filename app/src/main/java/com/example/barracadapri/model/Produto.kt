package com.example.barracadapri.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "produtos")
data class Produto(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nome: String,
    val preco: String,
    val quantidade: String
)


