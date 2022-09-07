package com.rahul.calendardayweekview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahul.calendardayweekview.Constant.selectdate

import com.rahul.calendardayweekview.databinding.WeekviewBinding
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class WeekView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet) {

    lateinit var binding: WeekviewBinding
    lateinit var adapter:CalendarAdapter
    lateinit var listner:AdapterClickListener

    init {
        // View.inflate(context,R.layout.activity_day,this)
        binding = WeekviewBinding.inflate(LayoutInflater.from(context), this)
      //  temp= selectdate
        setWeekView()
        binding.back.setOnClickListener {
            selectdate = selectdate.minusWeeks(1)
            Log.d("@date", selectdate.toString())
            setWeekView()
        }

        binding.forward.setOnClickListener {
            selectdate = selectdate.plusWeeks(1)
            Log.d("@date", selectdate.toString())
            setWeekView()
        }


    }

    private fun setWeekView() {

        binding.monthYearTV.setText(selectdate.month.toString() + " " + selectdate.year.toString())
        val days: ArrayList<LocalDateTime> = daysInWeekArray(selectdate)
         adapter = CalendarAdapter(days, object : AdapterClickListener {
            override fun onItemClick(view: View?, pos: Int, `object`: Any?) {
                Toast.makeText(context, days[pos].toLocalDate().toString(), Toast.LENGTH_SHORT).show()

                listner.onItemClick(view,pos,`object`)
                    adapter.notifyDataSetChanged()
            }



         })
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(context.applicationContext, 7)
        binding.weekrecycleview.setLayoutManager(layoutManager)
        binding.weekrecycleview.setAdapter(adapter)
        //  setEventAdpater()
    }

    fun daysInWeekArray(selectedDate: LocalDateTime): ArrayList<LocalDateTime> {
        val days = ArrayList<LocalDateTime>()//?
        var current = sundayForDate(selectedDate)
        val endDate = current!!.plusWeeks(1)
        while (current!!.isBefore(endDate)) {
            days.add(current)
            current = current.plusDays(1)
        }
        return days
    }

    private fun sundayForDate(current: LocalDateTime): LocalDateTime {
        var current = current
        val oneWeekAgo = current.minusWeeks(1)
        while (current.isAfter(oneWeekAgo)) {
            if (current.dayOfWeek == DayOfWeek.SUNDAY)
                return current
            current = current.minusDays(1)
        }
        return current//null
    }


}