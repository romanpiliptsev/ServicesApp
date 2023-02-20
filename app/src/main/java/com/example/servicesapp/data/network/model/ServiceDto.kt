package com.example.servicesapp.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ServiceDto (
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("description")
    @Expose
    val description: String? = null,
    @SerializedName("icon_url")
    @Expose
    val iconUrl: String? = null,
    @SerializedName("service_url")
    @Expose
    val serviceUrl: String? = null
)