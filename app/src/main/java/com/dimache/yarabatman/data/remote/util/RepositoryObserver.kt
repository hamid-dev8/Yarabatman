package com.dimache.yarabatman.data.remote.util

import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by User on 1/30/2018.
 *
 * observer for Repository Observables. handle error and wrapp methodes
 */

class RepositoryObserver<T>(var onResponse: (T?) -> Unit,
                            var onFailure: (ApiError?) -> Unit,
                            var onLogin: (ApiError?) -> Unit,
                            var onNotFound: (ApiError?) -> Unit,
                            var onBadRequest: (ApiError?) -> Unit,
                            var onSubscribef: (Disposable) -> Unit) : Observer<T> {


    override fun onSubscribe(d: Disposable) {
        onSubscribef(d)
    }


    override fun onNext(t: T) {
        if (t == null)
            onNotFound(null)
        else if(t is List<*> && t.isNullOrEmpty())
            onNotFound(null)
        else
            onResponse(t)
    }

    override fun onError(e: Throwable) {
        when (e) {
            is com.jakewharton.retrofit2.adapter.rxjava2.HttpException -> {
                val errorBody = e.response().errorBody()!!.string()
                val model: RemoteModel<Any, Any>?
                model = try {
                    //model = Moshi.Builder().build().adapter<RemoteModel<Any, Any>>(RemoteModel::class.java).fromJson(errorBody)
                    Gson().fromJson<RemoteModel<Any, Any>>(errorBody, RemoteModel::class.java)
                } catch (e1: Exception) {
                    RemoteModel(null, null, null)
                }
                when (e.code()) {
                    400 -> { onBadRequest(model?.error) }
                    404 -> { onNotFound(model?.error) }
                    402, 409, 500 -> onFailure(model?.error)
                    401 -> onLogin(model?.error)
                    else -> onFailure(model?.error)
                }
            }
            is NullPointerException -> onNotFound(null)
            else -> onFailure(null)
        }

    }

    override fun onComplete() {

    }


}
