package com.example.barracadapri.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var dbInstance: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "banco_barracadapri"
            ).build()
        }
        return dbInstance!!
    }
}