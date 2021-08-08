package com.xenrath.manusiabuahpembeli.ui.profile.changepassword

import com.xenrath.manusiabuahpembeli.data.database.model.ResponseProfileUpdate
import com.xenrath.manusiabuahpembeli.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordPresenter(val view: ChangePasswordContract.View): ChangePasswordContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun updatePassword(id: Long, password: String, password_confirmation: String) {
        view.onLoading(true)
        ApiService.endPoint.updatePassword(
            id,
            password,
            password_confirmation,
            "PUT"
        ).enqueue(object: Callback<ResponseProfileUpdate> {
            override fun onResponse(
                call: Call<ResponseProfileUpdate>,
                response: Response<ResponseProfileUpdate>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseProfileUpdate: ResponseProfileUpdate? = response.body()
                    view.onResult(responseProfileUpdate!!)
                }
            }

            override fun onFailure(call: Call<ResponseProfileUpdate>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }
}