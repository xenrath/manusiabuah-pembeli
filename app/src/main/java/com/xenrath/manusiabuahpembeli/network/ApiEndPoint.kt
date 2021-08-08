package com.xenrath.manusiabuahpembeli.network

import com.xenrath.manusiabuahpembeli.data.ResponseProduct
import com.xenrath.manusiabuahpembeli.data.ResponseLogin
import com.xenrath.manusiabuahpembeli.data.database.model.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiEndPoint {

    @FormUrlEncoded
    @POST("register")
    fun registerBuyer(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") password_confirmation: String,
        @Field("phone") phone: String,
        @Field("level") level: String,
    ): Call<ResponseLogin>

    @FormUrlEncoded
    @POST("login")
    fun loginBuyer(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("level") level: String
    ): Call<ResponseLogin>

    @GET("userDetail/{id}")
    fun getUserDetail(
        @Path("id") id: Long
    ): Call<ResponseUser>

    @Multipart
    @POST("profileupdate/{id}")
    fun updateProfile(
        @Path("id") id: Long,
        @Query("name") name: String,
        @Query("email") email: String,
        @Query("phone") phone: String,
        @Query("address") address: String?,
        @Part image: MultipartBody.Part?,
        @Query("_method") _method: String
    ): Call<ResponseProfileUpdate>

    @POST("password/{id}")
    fun updatePassword(
        @Path("id") id: Long,
        @Query("password") password: String,
        @Query("password_confirmation") password_confirmation: String,
        @Query("_method") _method: String
    ): Call<ResponseProfileUpdate>

    @POST("myproduct")
    fun myProduct(
        @Query("user_id") user_id: String
    ): Call<ResponseProductList>

    @POST("productsforsale")
    fun getProductForSale(
        @Query("user_id") user_id: String,
        @Query("category") category: String
    ): Call<ResponseProductList>

    @GET("searchproduct")
    fun searchProduct(
        @Query("keyword") keyword: String
    ): Call<ResponseProduct>

    @Multipart
    @POST("product")
    fun insertProduct(
        @Query("user_id") user_id: String,
        @Query("name") name: String,
        @Query("category") category: String,
        @Query("price") price: String,
        @Query("description") description: String?,
        @Query("address") address: String,
        @Query("province_id") province_id: String,
        @Query("province_name") province_name: String,
        @Query("city_id") city_id: String,
        @Query("city_name") city_name: String,
        @Query("postal_code") postal_code: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Part image: MultipartBody.Part,
        @Query("stock") stock: String,
    ): Call<ResponseProductUpdate>

    @GET("product/{id}")
    fun getProductDetail(
        @Path("id") id: Long
    ): Call<ResponseProductDetail>

    @Multipart
    @POST("product/{id}")
    fun updateProduct(
        @Path("id") id: Long,
        @Query("name") name: String,
        @Query("price") price: String,
        @Query("description") description: String,
        @Query("address") address: String,
        @Query("province_id") province_id: String,
        @Query("province_name") province_name: String,
        @Query("city_id") city_id: String,
        @Query("city_name") city_name: String,
        @Query("postal_code") postal_code: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Part image: MultipartBody.Part?,
        @Query("stock") stock: String,
        @Query("_method") _method: String
    ): Call<ResponseProductUpdate>

    @DELETE("product/{id}")
    fun deleteProduct(
        @Path("id") id: Long
    ): Call<ResponseProductUpdate>

    @POST("offerprice")
    fun offerPrice(
        @Query("user_id") user_id: String,
        @Query("product_id") product_id: String,
        @Query("price") price: String,
        @Query("price_offer") price_offer: String,
        @Query("total_item") total_item: String,
        @Query("status") status: String
    ): Call<ResponseBargain>

    @POST("myBargain")
    fun getMyBargain(
        @Query("user_id") user_id: String,
        @Query("status") status: String
    ): Call<ResponseBargainList>

    @GET("offerwaiting/{id}")
    fun getOfferWaiting(
        @Path("id") id: String
    ): Call<ResponseBargainList>

    @GET("offerreject/{id}")
    fun offerReject(
        @Path("id") id: Long
    ): Call<ResponseBargainUpdate>

    @GET("offeraccept/{id}")
    fun offerAccept(
        @Path("id") id: Long
    ): Call<ResponseBargainUpdate>

    @GET("offerhistory/{id}")
    fun offerHistory(
        @Path("id") id: String
    ): Call<ResponseBargainList>

    @POST("bargainAction/{id}")
    fun getBargainAction(
        @Path("id") id: Long,
        @Query("status") status: String,
        @Query("_method") _method: String
    ): Call<ResponseBargainUpdate>

    @GET("bargainDetail/{id}")
    fun getBargainDetail(
        @Path("id") id: Long
    ): Call<ResponseBargainDetail>

    @GET("bargainDetailDelivery/{id}")
    fun getBargainDetailDelivery(
        @Path("id") id: Long
    ): Call<ResponseBargainDetail>

    @GET("province")
    fun getProvince(
        @Header("key") key: String
    ): Call<ResponseRajaOngkirTerritory>

    @GET("city")
    fun getCity(
        @Header("key") key: String,
        @Query("province") id: String
    ): Call<ResponseRajaOngkirTerritory>

    @POST("addAddress")
    fun insertAddress(
        @Query("user_id") user_id: String,
        @Query("name") name: String,
        @Query("phone") phone: String,
        @Query("address") address: String,
        @Query("place") place: String,
        @Query("province_id") province_id: String,
        @Query("province_name") province_name: String,
        @Query("city_id") city_id: String,
        @Query("city_name") city_name: String,
        @Query("postal_code") postal_code: String
    ): Call<ResponseAddressUpdate>

    @POST("myAddress")
    fun getAddress(
        @Query("user_id") user_id: String
    ): Call<ResponseAddressList>

    @POST("checkAddress")
    fun checkAddress(
        @Query("id") id: Long,
        @Query("user_id") user_id: String,
    ): Call<ResponseAddressUpdate>

    @POST("addressChecked")
    fun addressChecked(
        @Query("user_id") user_id: String,
    ): Call<ResponseAddressDetail>

    @FormUrlEncoded
    @POST("cost")
    fun calculateCost(
        @Header("key") key: String,
        @Field("origin") origin: String,
        @Field("destination") destination: String,
        @Field("weight") weight: Int,
        @Field("courier") courier: String
    ): Call<ResponseRajaOngkirCost>

    @GET("getUser/{id}")
    fun getUser(
        @Path("id") id: Long
    ): Call<ResponseRajaOngkirCost>
}