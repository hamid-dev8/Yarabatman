package com.dimache.yarabatman

import android.app.Application
import android.content.Context
import com.bumptech.glide.request.target.ViewTarget
import com.dimache.yarabatman.data.CategoryDataSource
import com.dimache.yarabatman.data.DetailDataSource
import com.dimache.yarabatman.data.remote.util.RetrofitBuilder
import com.dimache.yarabatman.data.source.CategoryRepository
import com.dimache.yarabatman.data.source.DetailRepository
import com.dimache.yarabatman.detail.DetailViewModel
import com.dimache.yarabatman.main.CategoryViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App: Application()
{
    companion object {

        var appContext: Context? = null
            private set

        fun getContext() : Context? = appContext
    }

    private val appModule = module {

    }

    private val repoModule = module {
        single<CategoryDataSource> { CategoryRepository() }
        single<DetailDataSource> { DetailRepository() }
    }

    private val viewModelModule = module {
        viewModel { CategoryViewModel(get()) }
        viewModel { DetailViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        ViewTarget.setTagId(R.id.glide_custom_view_target_tag)
        RetrofitBuilder.init()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule, repoModule, viewModelModule)
        }

     /*   // or load with glide
        BigImageViewer.initialize(GlideImageLoader.with(appContext))*/
    }


}