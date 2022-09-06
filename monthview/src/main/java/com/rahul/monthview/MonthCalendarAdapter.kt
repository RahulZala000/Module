package com.rahul.monthview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahul.monthview.databinding.CalendarCellLayoutBinding
import java.time.LocalDate
import java.time.LocalDateTime

class MonthCalendarAdapter(var days: ArrayList<LocalDate>, var listner: AdapterClickListener):
    RecyclerView.Adapter<MonthCalendarAdapter.MyViewHolder>() {

    class MyViewHolder(var binding:CalendarCellLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CalendarCellLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var data=days[position]

        holder.binding.apply {
            cellDayText.text=data.dayOfMonth.toString()
        }
    }

    override fun getItemCount(): Int {
        return days.size
    }


}