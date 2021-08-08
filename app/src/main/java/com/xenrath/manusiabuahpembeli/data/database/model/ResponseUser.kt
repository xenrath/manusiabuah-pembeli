package com.xenrath.manusiabuahpembeli.data.database.model

import com.google.gson.annotations.SerializedName
import com.xenrath.manusiabuahpembeli.data.DataUser

data class ResponseUser(
    @SerializedName("status") val status: Boolean,
    @SerializedName("user") val user: DataUser
)
