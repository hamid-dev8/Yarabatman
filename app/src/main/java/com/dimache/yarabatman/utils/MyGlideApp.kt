package com.dimache.yarabatman.utils

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class MyGlideApp : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDiskCache(ExternalCacheDiskCacheFactory(context, "cache", DISK_CACHE_SIZE))
            .setMemoryCache(LruResourceCache(cacheSize.toLong()))
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

    companion object {
        private val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        private val cacheSize = maxMemory / 8
        private val DISK_CACHE_SIZE = 1024 * 1024 * 10
    }
}