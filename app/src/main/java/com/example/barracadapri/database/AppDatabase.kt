package com.example.barracadapri.database



import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.barracadapri.dao.ProdutoDao

import com.example.barracadapri.model.Produto

@Database(entities = [Produto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao
}