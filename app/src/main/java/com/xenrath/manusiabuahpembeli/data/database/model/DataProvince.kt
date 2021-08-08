package com.xenrath.manusiabuahpembeli.data.database.model

import com.google.gson.annotations.SerializedName

data class DataProvince(
    @SerializedName("id") val id: Long?,
    @SerializedName("nama") val name: String?
)