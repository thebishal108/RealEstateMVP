package com.example.realestateassignment.ui.main

import android.telecom.Call
import android.util.Log
import com.example.realestateassignment.Interface.ApiInterface
import com.example.realestateassignment.model.Data
import com.skeleton.mvp.ui.base.BasePresenterImpl
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainPresenterImpl(private val mMainView: MainInterface.MainView) : BasePresenterImpl(), MainInterface.MainPresenter {
    private val mMainInteractor: MainInterface.MainInteractor
    private lateinit var data : Data
    init {
        mMainInteractor = MainInteractorImpl()
    }

    override fun fetchApiData() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: ApiInterface = retrofit.create(ApiInterface::class.java)
        val repos = service.getData()
        repos?.enqueue(object : Callback<Data?>{
            override fun onResponse(call: retrofit2.Call<Data?>, response: Response<Data?>) {
                data = response.body()!!;
                Log.d("TAG",response.toString())
            }

            override fun onFailure(call: retrofit2.Call<Data?>, t: Throwable) {
                Log.d("TAG","error")
            }

        })
    }
}