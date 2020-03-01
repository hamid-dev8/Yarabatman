package com.dimache.yarabatman.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * wrapper of Schedulers used in application.
 * [.main] is android main thread (ui thread)
 * [.io] for network calls, database operations, file system and ... (io operation)
 * [.computation] for complex operations that run in another thread like as process image, compress file and ...
 */

object RxSchedulers {
    fun main(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    fun io(): Scheduler {
        return Schedulers.io()
    }

    fun computation(): Scheduler {
        return Schedulers.computation()
    }

}
