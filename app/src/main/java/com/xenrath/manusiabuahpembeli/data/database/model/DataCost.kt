package com.xenrath.manusiabuahpembeli.data.database.model

import com.google.gson.annotations.SerializedName

data class DataCost(
    @SerializedName("value") var value: Int?,
    @SerializedName("etd") var etd: String?,
    @SerializedName("note") var note: String?
)