package com.dimache.yarabatman.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dimache.yarabatman.App
import com.dimache.yarabatman.data.model.CategoryModel


@Database(entities = [CategoryModel::class],version = 2,exportSchema = true)
abstract class BatmanDatabase : RoomDatabase()
{
    private var INSTANCE : BatmanDatabase? = null
    abstract fun batmanDao() : BatmanDAO

    companion object{
        @Volatile
        private var INSTANCE : BatmanDatabase? = null

        fun getDataBase() : BatmanDatabase {
            return INSTANCE ?: synchronized(this){
                //create database here
                val instance = Room.databaseBuilder(App.appContext!!,
                    BatmanDatabase::class.java,
                    "batman_database"
                    ).allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }
}