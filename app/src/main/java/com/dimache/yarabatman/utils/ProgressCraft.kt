package com.dimache.yarabatman.utils

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.animation.*
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import com.dimache.yarabatman.R
import kotlinx.android.synthetic.main.layout_progress.view.*


class ProgressCraft: FrameLayout {

    val _r_animation = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
        duration = 1000
        repeatMode = Animation.RESTART
        repeatCount = Animation.INFINITE
        interpolator = LinearInterpolator()
    }

    val _f_animation = AnimationUtils.loadAnimation(context, R.anim.alpha).apply {
        repeatMode = Animation.REVERSE
        repeatCount = Animation.INFINITE
        interpolator = LinearInterpolator()
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )


    init {
        inflate(context, R.layout.layout_progress, this)
        pr_crl.animation = _r_animation
        show()
    }

    fun show() {
        visibility = View.VISIBLE
        _r_animation.start()
        _f_animation.start()
    }

    fun dismiss() {
        _r_animation.cancel()
        _f_animation.cancel()
        visibility = View.GONE
    }

}