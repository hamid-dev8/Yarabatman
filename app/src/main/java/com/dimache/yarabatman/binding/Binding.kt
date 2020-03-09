package com.dimache.yarabatman.binding

import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dimache.yarabatman.App
import com.dimache.yarabatman.data.model.CategoryModel
import com.dimache.yarabatman.main.MainOfflineAdapter
import com.dimache.yarabatman.utils.GlideCraft

object Binding {

    @BindingAdapter("app:cover")
    @JvmStatic
    fun setCover(imageView: ImageView, url: String?) {
        val rq = GlideCraft.getInstance()
        url?.let {
            rq.fullRequest!!
                .load(it)
                .into(imageView)
        }
    }

 /*   @BindingAdapter("app:off")
    @JvmStatic fun setReplies(listview: RecyclerView, replies: List<CategoryModel>?) {
        replies?.let {
            listview.apply {
                layoutManager = LinearLayoutManager(App.getContext())
                adapter = MainOfflineAdapter().apply { list = ArrayList(replies) }
            }
        }
    }*/


}