package com.dimache.yarabatman.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dimache.yarabatman.R
import com.dimache.yarabatman.base.BaseActivity
import com.dimache.yarabatman.utils.addFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getContainer(): Int = container.id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(MainFragment.newInstance(),"main",true)
    }

}
