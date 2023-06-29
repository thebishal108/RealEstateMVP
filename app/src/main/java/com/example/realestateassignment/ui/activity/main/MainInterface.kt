package com.example.realestateassignment.ui.activity.main

import com.example.realestateassignment.model.RealEstateListings
import com.example.realestateassignment.ui.adapter.FacilityAdapter
import com.skeleton.mvp.ui.base.BaseInterface

interface MainInterface {
    interface MainView : BaseInterface.BaseView {
        fun setFacility(realEstateListings: ArrayList<RealEstateListings>)
        fun updateFacility(realEstateListings: ArrayList<RealEstateListings>)
        fun showValidation()
    }
    interface MainPresenter : BaseInterface.BasePresenter {
        fun fetchData()
        fun updateData(position: Int,holder: FacilityAdapter.FacilityViewHolder?)
    }
    interface MainInteractor : BaseInterface.BaseInteractor {
    }
}