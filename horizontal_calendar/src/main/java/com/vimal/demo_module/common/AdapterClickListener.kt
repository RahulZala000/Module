package com.vimal.demo_module.common

import android.view.View

interface AdapterClickListener {
    fun onItemClick(view: View?, pos: Int, `object`: Any?)
}