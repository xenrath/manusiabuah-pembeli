package com.xenrath.manusiabuahpembeli.ui.user

import com.xenrath.manusiabuahpembeli.data.database.model.ResponseUser

interface UserContract {

    interface Presenter {
        fun getUser(id: Long)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(responseUser: ResponseUser)
        fun showMessage(message: String)
    }

}