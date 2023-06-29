package com.example.realestateassignment.ui.activity.main

import android.util.Log
import com.example.realestateassignment.Interface.ApiInterface
import com.example.realestateassignment.R
import com.example.realestateassignment.model.Data
import com.example.realestateassignment.model.RealEstateListings
import com.example.realestateassignment.ui.adapter.FacilityAdapter
import com.skeleton.mvp.ui.base.BasePresenterImpl
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainPresenterImpl(private val mMainView: MainInterface.MainView) : BasePresenterImpl(),
    MainInterface.MainPresenter {
    private val mMainInteractor: MainInterface.MainInteractor
    private lateinit var data : Data
    private var realEstateListings: ArrayList<RealEstateListings> = ArrayList()
    init {
        mMainInteractor = MainInteractorImpl()
    }

    override fun fetchData() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: ApiInterface = retrofit.create(ApiInterface::class.java)
        val repos = service.getData()
        repos?.enqueue(object : Callback<Data?>{
            override fun onResponse(call: retrofit2.Call<Data?>, response: Response<Data?>) {
                data = response.body()!!;
                for(facility in data.facilities){
                    realEstateListings.add(RealEstateListings(facility,null))
                }
                mMainView.setFacility(realEstateListings)
                Log.d("TAG",response.toString())
            }

            override fun onFailure(call: retrofit2.Call<Data?>, t: Throwable) {
                Log.d("TAG","error")
            }

        })
    }

    override fun updateData(position: Int, holder: FacilityAdapter.FacilityViewHolder?) {
        var relnew = ArrayList(realEstateListings)
        relnew.get(holder!!.adapterPosition).selectedOption = data.facilities.get(holder.adapterPosition).options.get(position)
        var flag:ArrayList<Boolean> = ArrayList()
        var fl:ArrayList<Boolean> = ArrayList()
        for(exclusion in data.exclusions){
            for (i in 0..exclusion.size-1) {
                for(rel in relnew){
                    if(rel.facility.facilityId.equals(exclusion.get(i).facilityId)){
                        if(rel.selectedOption?.id.equals(exclusion.get(i).optionsId)){
                            flag.add(true)
                        }else{
                            flag.add(false)
                        }
                    }
                }
            }
            fl.add(flag.all {it==true})
            flag=ArrayList()
        }
        if(fl.all{it==false}){
            realEstateListings.get(holder!!.adapterPosition).selectedOption = data.facilities.get(holder.adapterPosition).options.get(position)
            mMainView.updateFacility(realEstateListings)
        }else{
            mMainView.showValidation()
        }
    }
}