package com.dimache.yarabatman.utils

import com.dimache.yarabatman.data.remote.util.ApiValidation

interface StateCallback
{
    fun showProgres()
    fun dismisProgress()
    fun onError(e : String)
    fun onFieldsError(params : List<ApiValidation>)
    fun onNotFound(e : String)
    fun onSuccess()
}