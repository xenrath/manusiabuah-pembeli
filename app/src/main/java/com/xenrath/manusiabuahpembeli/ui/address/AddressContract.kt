package com.xenrath.manusiabuahpembeli.ui.address

import com.xenrath.manusiabuahpembeli.data.database.model.ResponseAddressList
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseAddressUpdate

interface AddressContract {

    interface Presenter {
        fun getAddress(user_id: String)
        fun checkAddress(
            id: Long,
            user_id: String
        )
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResultList(responseAddressList: ResponseAddressList)
        fun onResultChoice(responseAddressUpdate: ResponseAddressUpdate)
        fun showMessage(message: String)
    }

}