package com.dimache.yarabatman.data

import com.dimache.yarabatman.data.model.Movies
import com.dimache.yarabatman.data.model.Search
import io.reactivex.Observable

interface CategoryDataSource{

    fun getCategories() : Observable<List<Movies>>
}