package com.dimache.yarabatman.data.source

import com.dimache.yarabatman.data.CategoryDataSource
import com.dimache.yarabatman.data.model.Movies
import com.dimache.yarabatman.data.remote.RemoteDataSource
import com.dimache.yarabatman.data.remote.util.RetrofitBuilder
import io.reactivex.Observable

class CategoryRepository : RemoteDataSource(),CategoryDataSource
{
    init {
        api = RetrofitBuilder.getWebApi()
    }


    override fun getCategories(): Observable<List<Movies>> {
        return api.getCategories().flatMap { Observable.just(it.data) }
    }

}