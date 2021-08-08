package com.xenrath.manusiabuahpembeli.data.database.model

import com.google.gson.annotations.SerializedName

data class DataRajaOngkirCost(
    @SerializedName("status") val status: DataStatus,
    @SerializedName("results") val results: List<DataResultsCost>
)