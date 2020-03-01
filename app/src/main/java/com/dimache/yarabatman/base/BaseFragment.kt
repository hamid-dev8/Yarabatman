package com.dimache.yarabatman.base

import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.*

abstract class BaseFragment: Fragment()
{
    override fun onDestroy() {
        super.onDestroy()
        clearFindViewByIdCache()
    }
}