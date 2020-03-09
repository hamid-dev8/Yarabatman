package com.dimache.yarabatman.data

import com.dimache.yarabatman.data.model.CategoryModel
import com.dimache.yarabatman.data.model.Movies
import io.reactivex.Observable

interface CategoryDataSource{

    fun getCategories() : Observable<List<Movies>>
    fun saveOfflineMovies(list : List<Movies>,index : Int)
    fun getOfflineMovies() : Observable<List<CategoryModel>>

}