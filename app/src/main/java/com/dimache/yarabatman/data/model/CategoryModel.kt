package com.dimache.yarabatman.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "title")
    val title : String,
    @ColumnInfo(name = "year")
    val year : String,
    @ColumnInfo(name = "type")
    val type : String,
    @ColumnInfo(name = "poster")
    val poster : String,
    @ColumnInfo(name = "imdbID")
    val imdbID : String
)