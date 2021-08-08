package com.xenrath.manusiabuahpembeli.data.database.model

import com.google.gson.annotations.SerializedName

data class ResponseRajaOngkirTerritory(
    @SerializedName("status") val status: Boolean,
    @SerializedName("rajaongkir") val rajaongkir: DataRajaOngkirTerritory
)