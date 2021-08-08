package com.xenrath.manusiabuahpembeli.ui.delivery

import com.xenrath.manusiabuahpembeli.data.database.model.*

interface DeliveryContract {

    interface Presenter {
        fun getBargain(id: Long)
        fun getAddress(user_id: String)
        fun getCost(
            key: String,
            origin: String,
            destination: String,
            weight: Int,
            courier: String
        )
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onLoadingAddress(loading: Boolean)
        fun onLoadingCost(loading: Boolean)
        fun onResultBargain(responseBargainDetail: ResponseBargainDetail)
        fun onResultAddress(responseAddressDetail: ResponseAddressDetail)
        fun showSpinnerCourier()
        fun getCost(courier: String)
        fun onResultCost(responseRajaOngkirCost: ResponseRajaOngkirCost)
        fun showSpinnerCost(courier: String, costs: List<DataCosts>)
        fun setCost(costs: DataCosts)
        fun setTotal(value: Int)
        fun showMessage(message: String)
    }

}