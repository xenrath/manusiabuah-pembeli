package com.xenrath.manusiabuahpembeli.data.database.model

import com.google.gson.annotations.SerializedName

data class DataCosts(
    @SerializedName("service") var service: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("cost") var cost: List<DataCost>?,
    @SerializedName("is_active") var is_active: Boolean = false,
)