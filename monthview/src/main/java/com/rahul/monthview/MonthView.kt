package com.rahul.monthview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahul.monthview.Constant.selectdate
import com.rahul.monthview.databinding.MonthviewBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth

class MonthView @JvmOverloads constructor(context: Context,attributeSet: AttributeSet):LinearLayout(context,attributeSet) {

    lateinit var binding:MonthviewBinding
    lateinit var adapter:MonthCalendarAdapter
    lateinit var listner:AdapterClickListener

    init {
        // View.inflate(context,R.layout.activity_day,this)
        binding = MonthviewBinding.inflate(LayoutInflater.from(context), this)
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
        val days: ArrayList<LocalDate> = daysInMonthArray()
        adapter = MonthCalendarAdapter(days, object : AdapterClickListener {
            override fun onItemClick(view: View?, pos: Int, `object`: Any?) {
                Toast.makeText(context, days[pos].toLocalDate().toString(), Toast.LENGTH_SHORT).show()

                listner.onItemClick(view,pos,`object`)
                adapter.notifyDataSetChanged()
            }



        })
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(context.applicationContext, 7)
        binding.calendarRecyclerView.setLayoutManager(layoutManager)
        binding.calendarRecyclerView.setAdapter(adapter)
        //  setEventAdpater()
    }

    fun daysInMonthArray(): ArrayList<LocalDateTime> {
        val daysInMonthArray = ArrayList<LocalDateTime>()
        val yearMonth = YearMonth.from(selectdate)
        val daysInMonth = yearMonth.lengthOfMonth()
        val prevMonth: LocalDate = selectdate.minusMonths(1)
        val nextMonth: LocalDate = selectdate.plusMonths(1)
        val prevYearMonth = YearMonth.from(prevMonth)
        val prevDaysInMonth = prevYearMonth.lengthOfMonth()
        val firstOfMonth: LocalDate = selectdate.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value
        for (i in 1..42) {
            if (i <= dayOfWeek) daysIngitMonthArray.add(
                LocalDate.of(prevMonth.year, prevMonth.month, prevDaysInMonth + i - dayOfWeek))
            else if
                    (i > daysInMonth + dayOfWeek) daysInMonthArray.add(LocalDate.of(nextMonth.year, nextMonth.month, i - dayOfWeek - daysInMonth)) else
                daysInMonthArray.add(LocalDate.of(selectdate.getYear(), selectdate.getMonth(), i - dayOfWeek))
        }
        return daysInMonthArray
    }


}