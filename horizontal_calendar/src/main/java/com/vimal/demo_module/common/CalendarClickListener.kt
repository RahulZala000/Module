package com.vimal.demo_module.common

import android.view.View

interface CalendarClickListener {
    fun onItemClick(view: View?, pos: Int, `object`: Any?)
}