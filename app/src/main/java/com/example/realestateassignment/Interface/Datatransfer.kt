package com.example.realestateassignment.Interface

import com.example.realestateassignment.ui.adapter.FacilityAdapter.FacilityViewHolder

interface Datatransfer {
    fun onOptionSelect(position: Int, holder: FacilityViewHolder?)
}