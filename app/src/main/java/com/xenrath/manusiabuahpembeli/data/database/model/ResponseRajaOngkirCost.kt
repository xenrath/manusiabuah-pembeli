package com.xenrath.manusiabuahpembeli.data.database.model

import com.google.gson.annotations.SerializedName

data class ResponseRajaOngkirCost(
    @SerializedName("status") val status: Boolean,
    @SerializedName("rajaongkir") val rajaongkir: DataRajaOngkirCost
)