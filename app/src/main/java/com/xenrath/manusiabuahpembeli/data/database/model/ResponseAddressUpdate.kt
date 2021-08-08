package com.xenrath.manusiabuahpembeli.data.database.model

import com.google.gson.annotations.SerializedName

data class ResponseAddressUpdate(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String
)