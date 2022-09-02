package com.vimal.demo_module.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vimal.demo_module.adapter.CalendarAdapter
import com.vimal.demo_module.common.AdapterClickListener
import com.vimal.demo_module.common.CalendarClickListener
import java.util.*
import kotlin.collections.ArrayList

class RowCalendar(context: Context, attrs: AttributeSet) : RecyclerView(context, attrs) {

    private var dateList: ArrayList<Date> = ArrayList()

    var listener: CalendarClickListener? = null
    private var isInitialized = false
    private var mAdapter: CalendarAdapter? = null

    fun setDateList(startDate: Date, endDate: Date) {
        val dates = ArrayList<Date>()

        val date1: Date = startDate
        val date2: Date = endDate

        val cal1 = Calendar.getInstance()
        cal1.time = date1

        val cal2 = Calendar.getInstance()
        cal2.time = date2

        while (!cal1.after(cal2)) {
            dates.add(cal1.time)
            cal1.add(Calendar.DATE, 1)
        }

        dateList = dates
    }

    fun init() {
        this.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            mAdapter = CalendarAdapter(
                context,
                dateList,
                object : AdapterClickListener {
                    override fun onItemClick(view: View?, pos: Int, `object`: Any?) {
                        listener?.onItemClick(view, pos, `object`)
                    }
                })
            adapter = mAdapter
            setHasFixedSize(true)
            setItemViewCacheSize(dateList.size - 1)
            scrollToPosition(this@apply, dateList.size - 1)
            isInitialized = true
        }
    }

    private fun scrollToPosition(recyclerView: RecyclerView, pos: Int) {
        recyclerView.smoothScrollToPosition(pos)
    }

    fun moveToDate(date: Date) {
        if (isInitialized) {
            if (dateList.contains(date)) {
                mAdapter!!.moveToDate(date)
                scrollToPosition(this, dateList.indexOf(date))
            }
        }
    }

    fun reInit(startDate: Date, endDate: Date) {
        setDateList(startDate, endDate)
        init()
    }
}