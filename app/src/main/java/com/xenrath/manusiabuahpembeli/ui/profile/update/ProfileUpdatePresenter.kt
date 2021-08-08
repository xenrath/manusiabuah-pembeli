package com.xenrath.manusiabuahpembeli.ui.profile.update

import com.xenrath.manusiabuahpembeli.data.DataUser
import com.xenrath.manusiabuahpembeli.data.database.PrefManager
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseProfileUpdate
import com.xenrath.manusiabuahpembeli.network.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ProfileUpdatePresenter(val view: ProfileUpdateContract.View): ProfileUpdateContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun updateProfile(
        id: Long,
        name: String,
        email: String,
        phone: String,
        address: String?,
        image: File?
    ) {
        val requestBody: RequestBody
        val multipartBody: MultipartBody.Part

        if (image != null){
            requestBody = image.asRequestBody("image/*".toMediaTypeOrNull())
            multipartBody = MultipartBody.Part.createFormData("image", image.name, requestBody)
        } else {
            requestBody = "".toRequestBody("image/*".toMediaTypeOrNull())
            multipartBody = MultipartBody.Part.createFormData("image", "", requestBody)
        }
        view.onLoading(true)
        ApiService.endPoint.updateProfile(
            id,
            name,
            email,
            phone,
            address,
            multipartBody,
            "PUT"
        ).enqueue(object : Callback<ResponseProfileUpdate> {
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

    override fun setPref(prefManager: PrefManager, dataUser: DataUser) {
        prefManager.prefAddress = dataUser.address!!
        prefManager.prefImage = dataUser.image!!
    }

}