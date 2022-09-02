package stepper.module

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rahul.calendardayweekview.databinding.CalendarCellLayoutBinding
import stepper.module.databinding.EventCellBinding
import java.security.AccessController.getContext
import java.time.format.DateTimeFormatter

class EventAdapter():RecyclerView.Adapter<EventAdapter.MyViewHolder>(){

     var events: MutableList<EventModel>?=ArrayList()
    class MyViewHolder(var binding: EventCellBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(EventCellBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var date= events?.get(position)

        holder.binding.apply {

            var formate= DateTimeFormatter.ofPattern( "hh:mm")
            var time=date?.time?.format(formate)
            eventCellTV.text=date!!.name+" "+date.date.toString()+" "+date.time.toString()
        }
    }

    override fun getItemCount(): Int {
        return events!!.size
    }

    fun addevent(list:ArrayList<EventModel>){
        events!!.clear()
        events!!.addAll(list)
        Log.d("@data",list.toString())
        notifyDataSetChanged()
    }


}

