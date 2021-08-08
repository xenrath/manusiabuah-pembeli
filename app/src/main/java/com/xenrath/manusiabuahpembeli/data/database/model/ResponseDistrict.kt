package com.xenrath.manusiabuahpembeli.data.database.model

import com.google.gson.annotations.SerializedName

data class ResponseDistrict(
    @SerializedName("status") val status: Boolean,
    @SerializedName("kota_kabupaten") val disrict: List<DataDistrict>
)