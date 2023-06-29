package com.example.realestateassignment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Option {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("icon")
    @Expose
    var icon: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null
}