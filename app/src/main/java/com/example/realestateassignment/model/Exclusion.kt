package com.example.realestateassignment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Exclusion {
    @SerializedName("facility_id")
    @Expose
    var facilityId: String? = null

    @SerializedName("options_id")
    @Expose
    var optionsId: String? = null
}