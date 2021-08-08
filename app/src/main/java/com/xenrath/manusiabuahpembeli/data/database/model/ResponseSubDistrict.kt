package com.xenrath.manusiabuahpembeli.data.database.model

import com.google.gson.annotations.SerializedName

data class ResponseSubDistrict(
    @SerializedName("status") val status: Boolean,
    @SerializedName("kecamatan") val subdisrict: List<DataSubDistrict>
)