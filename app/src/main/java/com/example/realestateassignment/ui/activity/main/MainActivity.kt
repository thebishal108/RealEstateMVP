package com.example.realestateassignment.ui.activity.main

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.realestateassignment.Interface.Datatransfer
import com.example.realestateassignment.R
import com.example.realestateassignment.model.RealEstateListings
import com.example.realestateassignment.ui.adapter.FacilityAdapter
import com.skeleton.mvp.ui.base.BaseActivity


class MainActivity : BaseActivity(), MainInterface.MainView,Datatransfer{
    private lateinit var mMainPresenter: MainInterface.MainPresenter
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var mAdapter: FacilityAdapter? = null
    private var realEstateListings: ArrayList<RealEstateListings> = ArrayList()
    private lateinit var rvRealEstate : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mMainPresenter = MainPresenterImpl(this)
        mMainPresenter.onAttach()
        init()
    }
    private fun init() {
        mMainPresenter.fetchData()
        rvRealEstate=findViewById(R.id.rvRealEstate)
    }
    override fun onDestroy() {
        super.onDestroy()
        mMainPresenter.onDetach()
    }

    override fun setFacility(realEstateListings: ArrayList<RealEstateListings>) {
        this.realEstateListings = realEstateListings
        mLayoutManager = GridLayoutManager(this,2)
        mAdapter = FacilityAdapter(this, this.realEstateListings, this)
        rvRealEstate.setLayoutManager(mLayoutManager)
        rvRealEstate.setAdapter(mAdapter)
    }

    override fun updateFacility(realEstateListings: ArrayList<RealEstateListings>) {
        this.realEstateListings = this.realEstateListings
        mAdapter!!.notifyDataSetChanged()
        mAdapter!!.dismissDialog()
    }

    override fun showValidation() {
        Toast.makeText(this,getString(R.string.facility_not_available_in_this_combination),Toast.LENGTH_SHORT).show()
    }

    override fun onOptionSelect(position: Int, holder: Int) {
        mMainPresenter.updateData(position,holder)
    }
}