package com.dimache.yarabatman.data

import com.dimache.yarabatman.data.model.MovieDetail
import io.reactivex.Observable

interface DetailDataSource {

    fun getDetail(id : String) : Observable<MovieDetail>

}