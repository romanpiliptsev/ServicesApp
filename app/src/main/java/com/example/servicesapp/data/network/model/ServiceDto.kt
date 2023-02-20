package com.example.servicesapp.data.network.model

import com.google.gson.annotations.SerializedName

data class ServiceDto(
    val name: String? = null,
    val description: String? = null,
    @SerializedName("icon_url")
    val iconUrl: String? = null,
    @SerializedName("service_url")
    val serviceUrl: String? = null
)