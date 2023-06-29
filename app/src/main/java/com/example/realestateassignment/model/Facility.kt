package com.example.realestateassignment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Facility {
    @SerializedName("facility_id")
    @Expose
    var facilityId: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("options")
    @Expose
    var options: List<Option>? = null
}