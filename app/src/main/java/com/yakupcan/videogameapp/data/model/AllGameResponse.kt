package com.yakupcan.videogameapp.data.model

import com.google.gson.annotations.SerializedName
import com.yakupcan.videogameapp.data.model.Results

data class AllGameResponse(
    @SerializedName("count")
    var count: Int? = null,
    @SerializedName("next")
    var next: String? = null,
    @SerializedName("previous")
    var previous: String? = null,
    @SerializedName("results")
    var results: ArrayList<Results> = arrayListOf()
)