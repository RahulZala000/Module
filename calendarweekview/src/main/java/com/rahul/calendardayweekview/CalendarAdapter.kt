package com.rahul.calendardayweekview

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahul.calendardayweekview.Constant.selectdate
import com.rahul.calendardayweekview.databinding.CalendarCellLayoutBinding
import kotlinx.coroutines.selects.select
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.ArrayList

class CalendarAdapter(var days: ArrayList<LocalDateTime>, var listner: AdapterClickListener):RecyclerView.Adapter<CalendarAdapter.Myviewholder>() {

    var todaydate=LocalDateTime.now()
    var newdate:Int?=null

    class Myviewholder(var binding: CalendarCellLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myviewholder {
       return Myviewholder(CalendarCellLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: Myviewholder, position: Int) {
        var date=days[position]

        holder.binding.apply {
            cellDayText.text=date.dayOfMonth.toString()


            if ( date.toLocalDate()<todaydate.toLocalDate())
                cellDayText.setTextColor(Color.BLACK)
            else
                cellDayText.setTextColor(Color.LTGRAY)




       /*    if (date.toLocalDate() == todaydate.toLocalDate() ) {
                parentView.setBackgroundColor(Color.DKGRAY)
                if(position==newdate)
                    cellDayText.setTextColor(Color.WHITE)
            }
            else
                parentView.setBackgroundColor(Color.WHITE)*/

            if(position==newdate) {
                cellDayText.setTextColor(Color.WHITE)
                parentView.setBackgroundColor(Color.BLUE)
            }
            else
                parentView.setBackgroundColor(Color.WHITE)

            cellDayText.setOnClickListener {

                newdate=position
                listner.onItemClick(it,position,date)
            }
        }
    }

    override fun getItemCount(): Int {
        return days.size
    }

}
