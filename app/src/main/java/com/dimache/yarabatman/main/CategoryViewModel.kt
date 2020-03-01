package com.dimache.yarabatman.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimache.yarabatman.data.CategoryDataSource
import com.dimache.yarabatman.data.model.Movies
import com.dimache.yarabatman.utils.Response
import com.dimache.yarabatman.utils.RxSchedulers
import com.dimache.yarabatman.utils.subscribeBy

class CategoryViewModel(val repository : CategoryDataSource) : ViewModel()
{
    val categoriesLiveData = MutableLiveData<Response<List<Movies>>>()



    fun fetch()
    {
        repository.getCategories().subscribeOn(RxSchedulers.io())
            .observeOn(RxSchedulers.main())
            .subscribeBy (
                onResponse = {
                    categoriesLiveData.postValue(Response.success(it))
                },
                onNotFound = {

                },
                onFailure = {

                }
            )
    }

}