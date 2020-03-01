package com.dimache.yarabatman.utils

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import com.dimache.yarabatman.App
import com.dimache.yarabatman.R
import com.dimache.yarabatman.base.BaseActivity
import com.dimache.yarabatman.base.BaseFragment


fun AppCompatActivity.addFragment(fragment: Fragment, tag: String, backstack: Boolean, container: Int) {
    supportFragmentManager.transaction {
        setCustomAnimations(R.anim.fragment_open, R.anim.fragment_close, R.anim.fragment_open, R.anim.fragment_close)
        add(container, fragment, tag)
        if (backstack)
            addToBackStack(tag)
    }
}


fun BaseActivity.addFragment(fragment: Fragment, tag: String, backstack: Boolean) {
    addFragment(fragment, tag, backstack, getContainer())
}


fun Fragment.addFragment(fragment: Fragment, tag: String, backstack: Boolean, container: Int) {
    (activity as AppCompatActivity).addFragment(fragment, tag, backstack, container)
}


fun DialogFragment.addFragment(fragment: Fragment, tag: String, backstack: Boolean, container: Int) {
    (activity as AppCompatActivity).addFragment(fragment, tag, backstack, container)
}

fun BaseFragment.addFragment(fragment: Fragment, tag: String, backstack: Boolean) {
    if (activity is BaseActivity) {
        (activity as BaseActivity).addFragment(fragment, tag, backstack)
    }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, tag: String, backstack: Boolean, container: Int) {
    supportFragmentManager.transaction {
        setCustomAnimations(R.anim.fragment_open, R.anim.fragment_close, R.anim.fragment_open, R.anim.fragment_close)
        replace(container, fragment, tag)
        if (backstack)
            addToBackStack(tag)
    }
}


fun BaseActivity.replaceFragment(fragment: Fragment, tag: String, backstack: Boolean) {
    replaceFragment(fragment, tag, backstack, getContainer())
}


fun Fragment.replaceFragment(fragment: Fragment, tag: String, backstack: Boolean, container: Int) {
    (activity as AppCompatActivity).replaceFragment(fragment, tag, backstack, container)
}

fun DialogFragment.replaceFragment(fragment: Fragment, tag: String, backstack: Boolean, container: Int) {
    (activity as AppCompatActivity).replaceFragment(fragment, tag, backstack, container)
}


fun BaseFragment.replaceFragment(fragment: Fragment, tag: String, backstack: Boolean) {
    if (activity is BaseActivity) {
        (activity as BaseActivity).replaceFragment(fragment, tag, backstack)
    }
}

fun toast(msg: String) {
    Toast.makeText(App.appContext, msg, Toast.LENGTH_SHORT).show()
}

fun toast(msg: Int) {
    Toast.makeText(App.appContext, msg, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.openUrl(url: String) {
    val i = Intent(Intent.ACTION_VIEW)
    i.data = Uri.parse(url)
    startActivity(i)
}

fun Fragment.openUrl(url: String) {
    val i = Intent(Intent.ACTION_VIEW)
    i.data = Uri.parse(url)
    startActivity(i)
}

fun AppCompatActivity.openActivity(intent: Intent) {
    startActivity(intent)
    overridePendingTransition(R.anim.activity_open, R.anim.activity_close);
}

fun AppCompatActivity.openActivity(cls: Class<Any>) {
    openActivity(Intent(this, cls))
}

fun Fragment.openActivity(intent: Intent) {
    startActivity(intent)
    activity?.overridePendingTransition(R.anim.activity_open, R.anim.activity_close);
}

fun Fragment.openActivity(cls: Class<Any>) {
    openActivity(Intent(activity, cls))
}