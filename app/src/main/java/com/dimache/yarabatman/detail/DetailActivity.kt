package com.dimache.yarabatman.detail

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.dimache.yarabatman.R
import com.dimache.yarabatman.base.BaseActivity
import com.dimache.yarabatman.utils.addFragment
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity()
{

    override fun getContainer(): Int = container.id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val id = intent.getStringExtra("id")
        addFragment(DetailFragment.newInstance(id),"detail",false)
    }

}