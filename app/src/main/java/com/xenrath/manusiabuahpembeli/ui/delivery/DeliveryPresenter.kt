package com.xenrath.manusiabuahpembeli.ui.delivery

import com.xenrath.manusiabuahpembeli.data.database.model.ResponseAddressDetail
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseBargainDetail
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseRajaOngkirCost
import com.xenrath.manusiabuahpembeli.network.ApiService
import com.xenrath.manusiabuahpembeli.network.ApiServiceRajaOngkir
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeliveryPresenter(val view: DeliveryContract.View): DeliveryContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun getBargain(id: Long) {
        ApiService.endPoint.getBargainDetailDelivery(id).enqueue(object: Callback<ResponseBargainDetail> {
            override fun onResponse(
                call: Call<ResponseBargainDetail>,
                response: Response<ResponseBargainDetail>
            ) {
                if (response.isSuccessful) {
                    val responseBargainDetail: ResponseBargainDetail? = response.body()
                    view.onResultBargain(responseBargainDetail!!)
                }
            }

            override fun onFailure(call: Call<ResponseBargainDetail>, t: Throwable) {

            }

        })
    }

    override fun getAddress(user_id: String) {
        view.onLoadingAddress(true)
        ApiService.endPoint.addressChecked(user_id).enqueue(object : Callback<ResponseAddressDetail>{
            override fun onResponse(
                call: Call<ResponseAddressDetail>,
                response: Response<ResponseAddressDetail>
            ) {
                view.onLoadingAddress(false)
                if (response.isSuccessful) {
                    val responseAddressDetail: ResponseAddressDetail? = response.body()
                    view.onResultAddress(responseAddressDetail!!)
                }
            }

            override fun onFailure(call: Call<ResponseAddressDetail>, t: Throwable) {
                view.onLoadingAddress(false)
            }

        })
    }

    override fun getCost(
        key: String,
        origin: String,
        destination: String,
        weight: Int,
        courier: String
    ) {
        view.onLoadingCost(true)
        ApiServiceRajaOngkir.endPoint.calculateCost(
            key,
            origin,
            destination,
            weight,
            courier
        ).enqueue(object: Callback<ResponseRajaOngkirCost> {
            override fun onResponse(
                call: Call<ResponseRajaOngkirCost>,
                response: Response<ResponseRajaOngkirCost>
            ) {
                view.onLoadingCost(false)
                if (response.isSuccessful) {
                    val responseRajaOngkirCost: ResponseRajaOngkirCost? = response.body()
                    view.onResultCost(responseRajaOngkirCost!!)
                }
            }

            override fun onFailure(call: Call<ResponseRajaOngkirCost>, t: Throwable) {
                view.onLoadingCost(false)
            }

        })
    }

}