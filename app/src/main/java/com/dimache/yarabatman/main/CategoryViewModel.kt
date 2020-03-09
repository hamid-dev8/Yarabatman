package com.dimache.yarabatman.main

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimache.yarabatman.data.CategoryDataSource
import com.dimache.yarabatman.data.model.CategoryModel
import com.dimache.yarabatman.data.model.Movies
import com.dimache.yarabatman.utils.Response
import com.dimache.yarabatman.utils.RxSchedulers
import com.dimache.yarabatman.utils.subscribeBy

class CategoryViewModel(val repository : CategoryDataSource) : ViewModel()
{
    val categoriesLiveData = MutableLiveData<Response<List<Movies>>>()
    val offlineLiveData = MutableLiveData<List<CategoryModel>>()

    interface OnCategoryCallBack{
        fun onComplete(params : List<CategoryModel>)
    }

    var onCategoryCallBack : OnCategoryCallBack? = null

    fun setCategoryViewModel(callback : OnCategoryCallBack)
    {
        onCategoryCallBack = callback
    }

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

    fun saveCategory(list : List<Movies>,index : Int)
    {
        repository.saveOfflineMovies(list,index)
    }

    @SuppressLint("CheckResult")
    fun getCategory()
    {
        repository.getOfflineMovies().subscribeOn(RxSchedulers.io())
            .observeOn(RxSchedulers.main())
            .subscribe {
                offlineLiveData.postValue(it)
            }
        //onCategoryCallBack?.onComplete(repository.getOfflineMovies())
    }

    fun isCategoryEmpty() : List<CategoryModel>
    {
        return repository.getOfflineMovies().blockingFirst()
    }

}