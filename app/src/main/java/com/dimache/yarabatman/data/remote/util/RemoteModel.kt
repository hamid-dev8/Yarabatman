package com.dimache.yarabatman.data.remote.util

import com.google.gson.annotations.SerializedName


data class RemoteModel<T, E>(@SerializedName("Search")val data: T?, val meta: E?, val error: ApiError?)

data class ApiError(val message: String, @SerializedName("invalid_params") val params: List<ApiValidation>)

data class ApiValidation(val message: String, val field: String)