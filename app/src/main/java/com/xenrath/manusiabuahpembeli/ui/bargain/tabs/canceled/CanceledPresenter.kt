package com.xenrath.manusiabuahpembeli.ui.bargain.tabs.canceled

import com.xenrath.manusiabuahpembeli.data.database.model.ResponseBargainList
import com.xenrath.manusiabuahpembeli.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CanceledPresenter(val view: CanceledContract.View): CanceledContract.Presenter {

    override fun getBargainCanceled(
        user_id: String, status: String
    ) {
        view.onLoading(true)
        ApiService.endPoint.getMyBargain(user_id, status).enqueue(object: Callback<ResponseBargainList> {
            override fun onResponse(
                call: Call<ResponseBargainList>,
                response: Response<ResponseBargainList>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseBargainList: ResponseBargainList? = response.body()
                    view.onResult(responseBargainList!!)
                }
            }

            override fun onFailure(call: Call<ResponseBargainList>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

}