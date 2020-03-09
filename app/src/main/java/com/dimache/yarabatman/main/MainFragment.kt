package com.dimache.yarabatman.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimache.yarabatman.App
import com.dimache.yarabatman.R
import com.dimache.yarabatman.base.BaseFragment
import com.dimache.yarabatman.detail.DetailActivity
import com.dimache.yarabatman.utils.Status
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.net.NetworkInfo
import android.net.ConnectivityManager
import android.content.Context
import com.dimache.yarabatman.data.model.CategoryModel
import com.dimache.yarabatman.data.model.Movies

class MainFragment : BaseFragment() , CategoryViewModel.OnCategoryCallBack
{

    val viewModel: CategoryViewModel by viewModel()
    var movieAdapter : MainAdapter? = null
    var offlineAdapter : MainOfflineAdapter? = null


    companion object{
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main,container,false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        recycler.apply {
            layoutManager = LinearLayoutManager(App.appContext)
           // addItemDecoration(MarginDecoration(8, MarginDecoration.VERTICAL))
            val resId = R.anim.layout_animation_fall_down
            val animation = AnimationUtils.loadLayoutAnimation(context, resId)
            layoutAnimation = animation
        }


        progress.show()
        recycler.visibility = View.GONE


        viewModel.categoriesLiveData.observe(this, Observer {
            when(it.status)
            {
                Status.SUCCESS -> {
                    progress.dismiss()
                    recycler.visibility = View.VISIBLE
                    if (movieAdapter == null)
                    {
                        movieAdapter = MainAdapter().apply {
                            onItemClick = { movies, view ->

                                val intent = Intent(activity, DetailActivity::class.java).apply {
                                    putExtra("id", movies.imdbID)
                                }
                                startActivity(intent)
                            }

                        }

                        recycler.adapter = movieAdapter
                    }

                    movieAdapter?.list = it.data!!
                    if (viewModel.isCategoryEmpty().isEmpty()) {
                        it.data.forEachIndexed { index, movies ->
                            viewModel.saveCategory(it.data, index)
                        }
                    }
                    recycler.startAnimation(AnimationUtils.loadAnimation(App.appContext,R.anim.open_recycler))

                }
            }
        })

        viewModel.offlineLiveData.observe(this, Observer {
            progress.dismiss()
            recycler.visibility = View.VISIBLE
            recycler.adapter = offlineAdapter
            offlineAdapter?.list = it
            recycler.startAnimation(AnimationUtils.loadAnimation(App.appContext,R.anim.open_recycler))
        })

        viewModel.setCategoryViewModel(this)

        if (!isNetworkAvailable(App.appContext!!))
        {
            offlineAdapter = MainOfflineAdapter()
            viewModel.getCategory()
        }
        else{
            viewModel.fetch()
        }
    }

    override fun onComplete(params: List<CategoryModel>) {

    }


    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }
}