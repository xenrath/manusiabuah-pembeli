package com.xenrath.manusiabuahpembeli.data

class Constant {

    companion object {
        var IP = "http://192.168.1.3/tugas-akhir/fruitman/"
        var IP_IMAGE = IP + "public/storage/uploads/"

        var LATITUDE: String = ""
        var LONGITUDE: String = ""
        const val LOCATION_PERMISSION_REQUEST_CODE = 1

        var PRODUCT_ID: Long = 0
        var BARGAIN_ID: Long = 0

        var STOCK: Int = 1

        var PROVINCE_ID: String = "0"
        var PROVINCE_NAME: String = ""
        var CITY_ID: String = "0"
        var CITY_NAME: String = ""

        var ADDRESS_ID: Long = 0
    }

}