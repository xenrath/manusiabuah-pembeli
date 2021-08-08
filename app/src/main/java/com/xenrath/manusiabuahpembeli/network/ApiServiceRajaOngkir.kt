package com.xenrath.manusiabuahpembeli.network

import com.xenrath.manusiabuahpembeli.data.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceRajaOngkir {

    var BASE_URL = "https://api.rajaongkir.com/starter/"
    val endPoint: ApiEndPoint

    get() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiEndPoint::class.java)
    }

}