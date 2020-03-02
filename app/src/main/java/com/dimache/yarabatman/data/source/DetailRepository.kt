package com.dimache.yarabatman.data.source

import com.dimache.yarabatman.data.DetailDataSource
import com.dimache.yarabatman.data.model.MovieDetail
import com.dimache.yarabatman.data.remote.RemoteDataSource
import com.dimache.yarabatman.data.remote.util.RetrofitBuilder
import io.reactivex.Observable

class DetailRepository : RemoteDataSource() , DetailDataSource
{
    init {
        api = RetrofitBuilder.getWebApi()
    }


    override fun getDetail(id: String): Observable<MovieDetail> {
        return api.getDetail(id)
    }

}