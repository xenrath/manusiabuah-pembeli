package com.xenrath.manusiabuahpembeli.data.database.model

import com.google.gson.annotations.SerializedName

data class ResponseProductDetail(
    @SerializedName("status") val status: Boolean,
    @SerializedName("product") val product: DataProduct
)