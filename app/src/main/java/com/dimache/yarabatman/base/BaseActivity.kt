package com.dimache.yarabatman.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dimache.yarabatman.R
import kotlinx.android.synthetic.*

abstract class BaseActivity: AppCompatActivity()
{
    var active: Fragment? = null

    abstract fun getContainer() : Int

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.activity_open, R.anim.activity_close)
    }

    override fun onDestroy() {
        super.onDestroy()
        clearFindViewByIdCache()
    }

}