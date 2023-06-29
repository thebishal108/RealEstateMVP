package com.example.realestateassignment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("facilities")
    @Expose
    var facilities: List<Facility>? = null

    @SerializedName("exclusions")
    @Expose
    var exclusions: List<List<Exclusion>>? = null
}