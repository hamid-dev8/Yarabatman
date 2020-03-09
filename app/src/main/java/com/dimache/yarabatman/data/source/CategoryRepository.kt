package com.dimache.yarabatman.data.source

import com.dimache.yarabatman.data.CategoryDataSource
import com.dimache.yarabatman.data.local.BatmanDAO
import com.dimache.yarabatman.data.local.BatmanDatabase
import com.dimache.yarabatman.data.model.CategoryModel
import com.dimache.yarabatman.data.model.Movies
import com.dimache.yarabatman.data.remote.RemoteDataSource
import com.dimache.yarabatman.data.remote.util.RetrofitBuilder
import io.reactivex.Observable

class CategoryRepository : RemoteDataSource(),CategoryDataSource
{

    val dao: BatmanDAO = BatmanDatabase.getDataBase().batmanDao()


    override fun saveOfflineMovies(list: List<Movies>, index: Int) {
        dao.insert(CategoryModel(index,list[index].Title,list[index].Year,list[index].Type,list[index].Poster,list[index].imdbID))
    }

    override fun getOfflineMovies(): Observable<List<CategoryModel>> {
        return dao.getCategory()
    }


    init {
        api = RetrofitBuilder.getWebApi()
    }


    override fun getCategories(): Observable<List<Movies>> {
        return api.getCategories().flatMap { Observable.just(it.data) }
    }

}