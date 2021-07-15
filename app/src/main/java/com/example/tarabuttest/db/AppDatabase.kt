package com.example.tarabuttest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tarabuttest.App
import com.example.tarabuttest.dao.MatchDao
import com.example.tarabuttest.model.Match

@Database(
    entities = [Match :: class],
    version = AppDatabase.VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMatchDao(): MatchDao


    companion object{
        const val DB_NAME = "match.db"
        const val VERSION = 1

        private val instance: AppDatabase by lazy { create( App.instance) }

        @Synchronized
        internal fun getInstance(): AppDatabase {
            return instance
        }

        private fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }


    }


}