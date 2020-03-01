package com.dimache.yarabatman.utils

import com.dimache.yarabatman.data.remote.util.ApiError
import com.dimache.yarabatman.data.remote.util.RepositoryObserver
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

private val onResponseStub: (Any?) -> Unit = {}
private val onFailureStub: (ApiError?) -> Unit = {}
private val onNotFoundStub: (ApiError?) -> Unit = {}
private val onLoginStub: (ApiError?) -> Unit = {}
private val onBadRequestStub: (ApiError?) -> Unit = {}
private val onSubscribeStub: (Disposable) -> Unit = {}

fun <T : Any> Observable<T>.subscribeBy(
        onResponse: (T?) -> Unit = onResponseStub,
        onFailure: (ApiError?) -> Unit = onFailureStub,
        onNotFound: (ApiError?) -> Unit = onNotFoundStub,
        onLogin: (ApiError?) -> Unit = onLoginStub,
        onBadRequest: (ApiError?) -> Unit = onBadRequestStub,
        onSubscribe: (Disposable) -> Unit = onSubscribeStub
) = subscribe(RepositoryObserver<T>(onResponse, onFailure, onLogin, onNotFound, onBadRequest, onSubscribe))


fun <T : Any> Observable<T>.subscribeByf(
        onResponse: (T?) -> Unit = onResponseStub,
        onFailure: (ApiError?) -> Unit = onFailureStub,
        onNotFound: (ApiError?) -> Unit = onNotFoundStub,
        onLogin: (ApiError?) -> Unit = onLoginStub,
        onBadRequest: (ApiError?) -> Unit = onBadRequestStub,
        onSubscribe: (Disposable) -> Unit = onSubscribeStub
) = subscribeOn(RxSchedulers.io()).observeOn(RxSchedulers.main()).subscribe(RepositoryObserver<T>(onResponse, onFailure, onNotFound, onLogin, onBadRequest, onSubscribe))


