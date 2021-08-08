package com.xenrath.manusiabuahpembeli.data.database.model

import com.google.gson.annotations.SerializedName

data class ResponseAddressDetail(
    @SerializedName("status") val status: Boolean,
    @SerializedName("address") val address: List<DataAddress>
)