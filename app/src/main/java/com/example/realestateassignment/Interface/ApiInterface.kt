package com.example.realestateassignment.Interface

import com.example.realestateassignment.model.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {
    @GET("iranjith4/ad-assignment/db")
    fun getData(): Call<Data?>?
}