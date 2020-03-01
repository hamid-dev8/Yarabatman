package com.dimache.yarabatman.base

import androidx.lifecycle.ViewModel
import com.dimache.yarabatman.utils.StateCallback

abstract class BaseViewModel: ViewModel()
{
    internal var stateListener: StateCallback? = null

    fun setListener(s: StateCallback)
    {
        this.stateListener = s
    }



}