package com.dimache.yarabatman.data.remote.util

import com.dimache.yarabatman.data.remote.util.RemoteModel
import com.dimache.yarabatman.data.model.*
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by User on 11/28/2017.
 *
 * API endpoints.
 */

interface WebApi {

    @GET("?apikey=3e974fca&s=batman")
    fun getCategories() :Observable<RemoteModel<List<Movies>,Any>>




}

