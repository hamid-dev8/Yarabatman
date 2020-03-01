package com.dimache.yarabatman.utils



class Response<T> (val status: Status?, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): Response<T> {
            return Response(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String?): Response<T> {
            return Response(Status.ERROR, null, msg)
        }

        fun <T> loading(): Response<T> {
            return Response(Status.LOADING, null, null)
        }

        fun <T> notFound(msg: String?): Response<T> {
            return Response(Status.NOTFOUND, null, msg)
        }
    }
}
