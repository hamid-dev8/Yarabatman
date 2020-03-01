package com.dimache.yarabatman.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.dimache.yarabatman.App

class GlideCraft {

    val glideRequests = GlideApp.with(App.getContext()!!)
    var fullRequest: GlideRequest<Drawable>? = null
    var thumbRequest: GlideRequest<Drawable>? = null

    companion object {
        private var INSTANCE: GlideCraft? = null
        fun getInstance(): GlideCraft {
            return INSTANCE ?: synchronized(true) {
                val instance = GlideCraft().apply {
                    fullRequest = glideRequests
                        .asDrawable()
                        .centerCrop()
                        .placeholder(ColorDrawable(Color.parseColor("#f0f0f0")))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                    thumbRequest = glideRequests
                        .asDrawable()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(ColorDrawable(Color.parseColor("#f0f0f0")))
                        .transition(withCrossFade())
                }
                INSTANCE = instance
                instance
            }
        }
    }


    fun getPreviewRequest(view: View): GlideRequest<Drawable> {
        return fullRequest!!.override(100, 100)
    }






}