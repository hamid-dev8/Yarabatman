package com.dimache.yarabatman.data.local

import androidx.annotation.WorkerThread
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dimache.yarabatman.data.model.CategoryModel
import com.dimache.yarabatman.data.model.Movies
import io.reactivex.Observable

@Dao
interface BatmanDAO {

    @WorkerThread
    @Query("SELECT * from category")
    fun getCategory() : Observable<List<CategoryModel>>

    @WorkerThread
    @Insert
    fun insert(model: CategoryModel)

}