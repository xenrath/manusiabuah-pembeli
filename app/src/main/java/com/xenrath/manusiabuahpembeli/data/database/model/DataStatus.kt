package com.xenrath.manusiabuahpembeli.data.database.model

import com.google.gson.annotations.SerializedName

data class DataStatus(
    @SerializedName("code") val code: Int,
    @SerializedName("description") val desription: String
)