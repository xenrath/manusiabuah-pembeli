package com.xenrath.manusiabuahpembeli.ui.profile.update

import com.xenrath.manusiabuahpembeli.data.DataUser
import com.xenrath.manusiabuahpembeli.data.database.PrefManager
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseProfileUpdate
import java.io.File

interface ProfileUpdateContract {

    interface Presenter {
        fun updateProfile(
            id: Long,
            name: String,
            email: String,
            phone: String,
            address: String?,
            image: File?
        )
        fun setPref(
            prefManager: PrefManager,
            dataUser: DataUser
        )
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(responseProfileUpdate: ResponseProfileUpdate)
        fun showMessage(message: String)
    }

}