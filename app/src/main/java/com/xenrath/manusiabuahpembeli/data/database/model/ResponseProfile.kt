package com.xenrath.manusiabuahpembeli.data.database.model

import com.google.gson.annotations.SerializedName
import com.xenrath.manusiabuahpembeli.data.DataUser

data class ResponseProfile(
    @SerializedName("status") val status: Boolean,
    @SerializedName("profile") val profile: DataUser
)
