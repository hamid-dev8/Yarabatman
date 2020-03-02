package com.dimache.yarabatman.detail

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimache.yarabatman.data.DetailDataSource
import com.dimache.yarabatman.data.model.MovieDetail
import com.dimache.yarabatman.data.model.Movies
import com.dimache.yarabatman.utils.Response
import com.dimache.yarabatman.utils.RxSchedulers
import com.dimache.yarabatman.utils.subscribeBy

class DetailViewModel(val repository : DetailDataSource) : ViewModel()
{
    val detailField  = ObservableField<MovieDetail>()



    fun fetchDetail(id : String)
    {
        repository.getDetail(id).subscribeOn(RxSchedulers.io())
            .observeOn(RxSchedulers.main())
            .subscribeBy(
                onResponse = {
                    detailField.set(it)
                },
                onFailure = {

                },
                onNotFound = {

                }
            )
    }

}