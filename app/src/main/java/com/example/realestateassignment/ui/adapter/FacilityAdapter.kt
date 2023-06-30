package com.example.realestateassignment.ui.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.realestateassignment.Interface.Datatransfer
import com.example.realestateassignment.R
import com.example.realestateassignment.model.Option
import com.example.realestateassignment.model.RealEstateListings
import com.example.realestateassignment.ui.adapter.FacilityAdapter.FacilityViewHolder
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class FacilityAdapter(
    private val context: Context,
    private val realEstateListings: ArrayList<RealEstateListings>,
    private val datatransfer: Datatransfer
) : RecyclerView.Adapter<FacilityViewHolder>() {
    private var optionSelectVehicleDialog: BottomSheetDialog? = null

    class FacilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivImage: ImageView
        var tvName: TextView
        var tvOptions: TextView

        init {
            ivImage = itemView.findViewById(R.id.ivImage)
            tvName = itemView.findViewById(R.id.tvName)
            tvOptions = itemView.findViewById(R.id.tvOptions)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_facility_layout, parent, false)
        return FacilityViewHolder(v)
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        holder.tvName.text = realEstateListings[position].facility.name
        if (realEstateListings[position].selectedOption != null) {
            holder.tvOptions.text = realEstateListings[position].selectedOption!!.name
        } else {
            holder.tvOptions.setText(R.string.select_option)
        }
        if (realEstateListings[position].img != 0) {
            holder.ivImage.setImageResource(realEstateListings[position].img)
        }
        holder.tvOptions.setOnClickListener {
            var etSearch: EditText
            val ivClose: ImageView?
            val tvDialogHeader: TextView?
            val rvItemSelect: RecyclerView?
            val optionSelectAdapter: OptionSelectAdapter
            optionSelectVehicleDialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
            optionSelectVehicleDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val bottomSheetView =
                LayoutInflater.from(context).inflate(R.layout.dialog_option_item_selection, null)
            optionSelectVehicleDialog!!.setContentView(bottomSheetView)
            val bottomSheetBehavior: BottomSheetBehavior<*> =
                BottomSheetBehavior.from(bottomSheetView.parent as View)
            bottomSheetBehavior.peekHeight = ViewGroup.LayoutParams.MATCH_PARENT
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                500f,
                context.resources.displayMetrics
            )
            tvDialogHeader = optionSelectVehicleDialog!!.findViewById(R.id.tvDialogHeader)
            ivClose = optionSelectVehicleDialog!!.findViewById(R.id.ivClose)
            rvItemSelect = optionSelectVehicleDialog!!.findViewById(R.id.rvItemSelect)
            tvDialogHeader!!.text = "Select Vehicle"
            val vehicleLayoutManager = LinearLayoutManager(context)
            rvItemSelect!!.layoutManager = vehicleLayoutManager
            optionSelectAdapter = OptionSelectAdapter(
                context,
                realEstateListings[holder.adapterPosition].facility.options as ArrayList<Option>,
                holder.adapterPosition,
                datatransfer
            )
            rvItemSelect.adapter = optionSelectAdapter
            ivClose!!.setOnClickListener { optionSelectVehicleDialog!!.dismiss() }
            optionSelectVehicleDialog!!.show()
        }
    }

    override fun getItemCount(): Int {
        return realEstateListings.size
    }

    fun dismissDialog() {
        optionSelectVehicleDialog!!.dismiss()
    }
}