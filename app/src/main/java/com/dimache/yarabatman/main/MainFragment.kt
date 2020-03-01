package com.dimache.yarabatman.main

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
import com.dimache.yarabatman.utils.Status
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment()
{
    val viewModel: CategoryViewModel by viewModel()
    var movieAdapter : MainAdapter? = null


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

                        }

                        recycler.adapter = movieAdapter
                    }

                    movieAdapter?.list = it.data!!
                    recycler.startAnimation(AnimationUtils.loadAnimation(App.appContext,R.anim.open_recycler))

                }
            }
        })


        viewModel.fetch()
    }

}