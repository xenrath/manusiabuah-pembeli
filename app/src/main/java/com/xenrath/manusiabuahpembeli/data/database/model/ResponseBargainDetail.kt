package com.xenrath.manusiabuahpembeli.data.database.model

import com.google.gson.annotations.SerializedName

data class ResponseBargainDetail(
    @SerializedName("status") val status: Boolean,
    @SerializedName("bargain") val bargain: DataBargain
)