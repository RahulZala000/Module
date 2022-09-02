package com.rahul.horizontal_stepper

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class Stepper(context:Context,attrs:AttributeSet) : RecyclerView(context,attrs) {

    private var dateList: ArrayList<String> = ArrayList()
    var first=0;
    var last=0;
    lateinit var listner:AdapterClickListener

    fun setdata(data: ArrayList<String>, i: Int, size: Int){
        dateList=data
        first=i
        last=size
    }


    fun init() {
        this.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = StepperAdapter(
                context,
                dateList,
                first,
                last,
                object : AdapterClickListener {
                    override fun onItemClick(view: View?, pos: Int, `object`: Any?) {
                        listner?.onItemClick(view, pos, `object`)
                    }

                })
            setHasFixedSize(true)
            setItemViewCacheSize(dateList.size - 1)
         //   scrollToPosition(this@apply, dateList.size - 1)
          //  isInitialized = true
        }
    }
}