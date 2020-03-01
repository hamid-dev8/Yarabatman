package com.dimache.yarabatman.data.remote

import com.dimache.yarabatman.data.remote.util.RetrofitBuilder

open class RemoteDataSource
{
    var api = RetrofitBuilder.getWebApi()
}