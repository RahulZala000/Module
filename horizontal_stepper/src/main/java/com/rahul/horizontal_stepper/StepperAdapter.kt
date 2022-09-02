package com.rahul.horizontal_stepper

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahul.horizontal_stepper.databinding.LayoutStepperBinding


class StepperAdapter(
    var context: Context,
    var click: List<String>,
    var selectedItem: Int,
    var maxValue: Int,
    var listner: AdapterClickListener
) :

    RecyclerView.Adapter<StepperAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: LayoutStepperBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutStepperBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {

        if(position==0)
            holder.binding.look0.background=context.getDrawable(R.color.white)
        else
            holder.binding.look0.background=context.getDrawable(R.color.teal_700)

        if (position == click.size - 1)
            holder.binding.look1.visibility = View.GONE
        else
            holder.binding.look1.visibility = View.VISIBLE

        var item=click[position]

        holder.binding.apply {
            holder.itemView.tag = click.get(position)

            tvPets.text=(position+1).toString()
            tvTextt.text= click.get(position)
            if (selectedItem == position) {
                holder.binding.tvPets.background =
                    context.resources.getDrawable(R.drawable.radio_currunt)
                holder.binding.tvTextt.setTextColor(context.resources.getColor(R.color.teal_700))
                holder.binding.tvPets.setTextColor(context.resources.getColor(R.color.teal_700))

            } else if (maxValue >= position) {
                holder.binding.tvPets.background =
                    context.resources.getDrawable(R.drawable.radio_selected)
                holder.binding.tvTextt.setTextColor(context.resources.getColor(R.color.black))
                holder.binding.tvPets.setTextColor(context.resources.getColor(R.color.white))
            }

            holder.binding.tvPets.setOnClickListener {
                if (maxValue >= position) {
                    listner.onItemClick(it,position,item)
                    val previousItem: Int = selectedItem
                    selectedItem = position
                    notifyItemChanged(previousItem)
                    notifyItemChanged(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return click.size
    }
}