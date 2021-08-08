package com.xenrath.manusiabuahpembeli.data.database.model

import com.google.gson.annotations.SerializedName

data class DataDistrict(
    @SerializedName("id") val id: Long?,
    @SerializedName("id_provinsi") val id_province: String?,
    @SerializedName("nama") val name: String?
)