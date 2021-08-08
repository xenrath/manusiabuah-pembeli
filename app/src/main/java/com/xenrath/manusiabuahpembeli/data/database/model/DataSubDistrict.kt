package com.xenrath.manusiabuahpembeli.data.database.model

import com.google.gson.annotations.SerializedName

data class DataSubDistrict(
    @SerializedName("id") val id: Long?,
    @SerializedName("id_kota") val id_district: String?,
    @SerializedName("nama") val name: String?
)