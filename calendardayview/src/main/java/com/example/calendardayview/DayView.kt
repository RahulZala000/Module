package com.example.calendardayview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calendardayview.Constant.selectdate
import com.example.calendardayview.databinding.LayoutDayviewBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DayView @JvmOverloads constructor(context: Context,attributeSet: AttributeSet):LinearLayout(context,attributeSet) {

    var binding:LayoutDayviewBinding
     var listner:TextChangeListner?=null
    private var dateList: ArrayList<LocalDateTime> = ArrayList()

    init {
        binding = LayoutDayviewBinding.inflate(LayoutInflater.from(context), this)
        setDayView()
        binding.back.setOnClickListener {
            selectdate = selectdate.minusDays(1)
            Log.d("@date", selectdate.toString())
            setDayView()
        }

        binding.forward.setOnClickListener {
            selectdate = selectdate.plusDays(1)
            Log.d("@date", selectdate.toString())
            setDayView()
        }


        binding.monthDayText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                 listner?.onItem(selectdate)
            }

        })
    }

    fun setDayView(){
       // listner.onItem(selectdate)
        val formatter = DateTimeFormatter.ofPattern("MMMM d")
        binding.monthDayText.text= selectdate.format(formatter)
        binding.dayOfWeekTV.text= selectdate.dayOfWeek.toString()
    }
}