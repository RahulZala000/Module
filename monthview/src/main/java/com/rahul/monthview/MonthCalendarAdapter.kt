package com.rahul.monthview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate

class MonthCalendarAdapter(var days: ArrayList<LocalDate?>, var listner: AdapterClickListener):
    RecyclerView.Adapter<MonthCalendarAdapter.MyViewHolder>() {

    var newdate:Int?=null
    var month=LocalDate.now()

    class MyViewHolder(var itemView: View):RecyclerView.ViewHolder(itemView){

        var datebox=itemView.findViewById<TextView>(R.id.cellDayText)
        var parentview=itemView.findViewById<ConstraintLayout>(R.id.parentView)

        fun bind(){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      //  var view= MyViewHolder(CalendarCellLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.calendar_cell_layout, parent, false)
        val layoutParams: ViewGroup.LayoutParams =view.layoutParams
        layoutParams.height = (parent.height * 0.166).toInt()
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var data= days.get(position)



        holder.apply {


            if(data!=null)
                holder.datebox.setText(data.dayOfMonth.toString())
            else
                holder.datebox.setText("")

            if(position==newdate) {
               // cellDayText.setTextColor(Color.WHITE)
                parentview.setBackgroundColor(Color.BLUE)
            }
            else
                parentview.setBackgroundColor(Color.WHITE)

            if ( data?.monthValue==month.monthValue)
                datebox.setTextColor(Color.BLACK)
            else
                datebox.setTextColor(Color.LTGRAY)

            datebox.setOnClickListener {

                newdate=position
                listner.onItemClick(it,position,data)
            }
        }
    }

    override fun getItemCount(): Int {
        return days.size
    }


}