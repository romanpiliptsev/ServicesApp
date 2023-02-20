package com.example.servicesapp.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ServicesResponseDto (
    @SerializedName("items")
    @Expose
    val items: List<ServiceDto>? = null
)