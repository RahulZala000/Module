package com.vimal.demo_module.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.vimal.demo_module.R
import com.vimal.demo_module.common.AdapterClickListener
import com.vimal.demo_module.databinding.RowCalendarBinding
import java.text.SimpleDateFormat
import java.util.*

class CalendarAdapter(
    private val context: Context,
    private val data: List<Date>,
    mListener: AdapterClickListener,
) : RecyclerView.Adapter<CalendarAdapter.MyViewHolder>() {

    private var listener = mListener
    private var index = -1
    // This is true only the first time you load the calendar.
    private var selectCurrentDate = true

    private val daySdf = SimpleDateFormat("dd", Locale.ENGLISH)
    private val monthSdf = SimpleDateFormat("MM", Locale.ENGLISH)
    private val yearSdf = SimpleDateFormat("yyyy", Locale.ENGLISH)

    private var selectedDay = daySdf.format(data[data.size - 1]).toInt()
    private var selectedMonth = monthSdf.format(data[data.size - 1]).toInt()
    private var selectedYear = yearSdf.format(data[data.size - 1]).toInt()

    class MyViewHolder(val binding: RowCalendarBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RowCalendarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.apply {

            val sdf = SimpleDateFormat("EEE", Locale.ENGLISH)

            val date = data[position]

            val displayDay = daySdf.format(date).toInt()
            val displayMonth = monthSdf.format(date).toInt()
            val displayYear = yearSdf.format(date).toInt()

            tvDay.text = sdf.format(date).toString().replaceFirstChar { it.uppercase() }
            tvDate.text = displayDay.toString()

            clCalender.setOnClickListener {
                index = position
                selectCurrentDate = false
                notifyDataSetChanged()
            }

            if (index == position) {
                makeItemSelected(holder)
                listener.onItemClick(
                    clCalender,
                    position,
                    date
                )
            } else {
                if (displayDay == selectedDay
                    && displayMonth == selectedMonth
                    && displayYear == selectedYear
                    && selectCurrentDate
                ) {
                    makeItemSelected(holder)
                    listener.onItemClick(
                        clCalender,
                        position,
                        date
                    )
                } else
                    makeItemDefault(holder)
            }

        }
    }

    /**
     * This make the item selected.
     */
    private fun makeItemSelected(holder: MyViewHolder) {
        holder.binding.apply {
            clCalender.setBackgroundResource(R.drawable.dr_bg_button_green)
            tvDay.setTextColor(ContextCompat.getColor(context, R.color.white))
            tvDate.setTextColor(ContextCompat.getColor(context, R.color.white))
            clCalender.isEnabled = false
        }
    }

    /**
     * This make the item default.
     */
    private fun makeItemDefault(holder: MyViewHolder) {
        holder.binding.apply {
            clCalender.setBackgroundResource(R.drawable.dr_bg_button_white)
            tvDay.setTextColor(ContextCompat.getColor(context, R.color.black))
            tvDate.setTextColor(ContextCompat.getColor(context, R.color.black))
            clCalender.isEnabled = true
        }
    }

    /**
     * This make the item disabled.
     */
    private fun makeItemDisabled(holder: MyViewHolder) {
        holder.binding.apply {
            clCalender.setBackgroundResource(R.drawable.dr_bg_button_white)
            tvDay.setTextColor(ContextCompat.getColor(context, R.color.colorGrayLight))
            tvDate.setTextColor(ContextCompat.getColor(context, R.color.colorGrayLight))
            clCalender.isEnabled = false
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun moveToDate(selectedDate: Date) {
        selectCurrentDate = true
        selectedDay = daySdf.format(selectedDate).toInt()
        selectedMonth = monthSdf.format(selectedDate).toInt()
        selectedYear = yearSdf.format(selectedDate).toInt()
        Log.d("RowCalendar", "$selectedDay-$selectedMonth-$selectedYear")
        notifyDataSetChanged()
    }

}