package com.xenrath.manusiabuahpembeli.data.database.model

import com.google.gson.annotations.SerializedName

data class DataResultsCost(
    @SerializedName("code") val code: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("costs") val costs: List<DataCosts>?
)