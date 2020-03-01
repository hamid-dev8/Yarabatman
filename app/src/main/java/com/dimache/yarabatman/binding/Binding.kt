package com.dimache.yarabatman.binding

import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.dimache.yarabatman.utils.GlideCraft

object Binding {

    @BindingAdapter("app:preview")
    @JvmStatic
    fun setPreview(imageView: ImageView, url: String?) {
        val rq = GlideCraft.getInstance()
        url?.let {
            rq.fullRequest!!
                .override(300, 300)
                .load(it)
                .into(imageView)
        }
    }

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

}