package com.xenrath.manusiabuahpembeli.ui.profile.changepassword

import com.xenrath.manusiabuahpembeli.data.database.model.ResponseProfileUpdate

interface ChangePasswordContract {

    interface Presenter {
        fun updatePassword(
            id: Long,
            password: String,
            password_confirmation: String
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