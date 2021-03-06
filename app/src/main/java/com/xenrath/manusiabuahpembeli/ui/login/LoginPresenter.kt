package com.xenrath.manusiabuahpembeli.ui.login

import com.xenrath.manusiabuahpembeli.data.DataUser
import com.xenrath.manusiabuahpembeli.data.ResponseLogin
import com.xenrath.manusiabuahpembeli.data.database.PrefManager
import com.xenrath.manusiabuahpembeli.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val view: LoginContract.View): LoginContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun doLogin(email: String, password: String, level: String) {
        view.onLoading(true)
        ApiService.endPoint.loginBuyer(
            email,
            password,
            level
        ).enqueue(object: Callback<ResponseLogin> {
            override fun onResponse(
                call: Call<ResponseLogin>,
                response: Response<ResponseLogin>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseLogin: ResponseLogin? = response.body()
                    view.showMessage(responseLogin!!.message)

                    if (responseLogin.status) {
                        view.onResult(responseLogin)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

    override fun setPref(prefManager: PrefManager, dataUser: DataUser) {
        prefManager.prefLogin = true
        prefManager.prefId = dataUser.id!!
        prefManager.prefName = dataUser.name!!
        prefManager.prefEmail = dataUser.email!!
        prefManager.prefPassword = dataUser.password!!
        prefManager.prefPhone = dataUser.phone!!
        prefManager.prefLevel = dataUser.level!!
    }

}