package com.dimache.yarabatman.data.sp

import android.content.Context
import com.dimache.yarabatman.App
import com.dimache.yarabatman.BuildConfig
import java.util.*

object SPManager
{
    private val sharedPreferences = App.getContext()!!.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()
    private var uniqueID: String? = null

    @JvmStatic
    fun setToken(token: String) {
        editor.putString("token", token)
        editor.apply()
    }

    @JvmStatic
    fun getToken(): String {
        return "Bearer " + sharedPreferences.getString("token", "")!!
    }

    @JvmStatic
    fun getUserName(): String? {
        return sharedPreferences.getString("user_name", "")
    }

    @JvmStatic
    fun setFCMTopic(topics: Set<String>) {
        editor.putStringSet("topics", topics)
        editor.apply()
    }

    @JvmStatic
    fun getFCMTopic(): Set<String>? {
        return sharedPreferences.getStringSet("topics", setOf())
    }

    @JvmStatic
    @Synchronized
    fun getUuid(): String? {
        uniqueID = sharedPreferences.getString("UUID", null)
        if (uniqueID == null) {
            uniqueID = UUID.randomUUID().toString()
            editor.putString("UUID", uniqueID)
            editor.commit()
        }
        return uniqueID
    }





}