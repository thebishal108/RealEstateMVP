package com.example.realestateassignment.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.realestateassignment.Interface.Datatransfer
import com.example.realestateassignment.R
import com.example.realestateassignment.model.Option
import com.example.realestateassignment.ui.adapter.FacilityAdapter.FacilityViewHolder
import com.example.realestateassignment.ui.adapter.OptionSelectAdapter.OptionSelectViewHolder

class OptionSelectAdapter(
    private val context: Context,
    private val options: ArrayList<Option>,
    private val holder1: FacilityViewHolder,
    private val datatransfer: Datatransfer
) : RecyclerView.Adapter<OptionSelectViewHolder>() {
    class OptionSelectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvOption: TextView
        val llContainer: LinearLayout

        init {
            tvOption = itemView.findViewById(R.id.tvOption)
            llContainer = itemView.findViewById(R.id.llContainer)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionSelectViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_option_select, parent, false)
        return OptionSelectViewHolder(v)
    }

    override fun onBindViewHolder(holder: OptionSelectViewHolder, position: Int) {
        holder.tvOption.text = options[position].name
        holder.llContainer.setOnClickListener {
            datatransfer.onOptionSelect(
                position,
                holder1
            )
        }
    }

    override fun getItemCount(): Int {
        return options.size
    }
}