package com.xenrath.manusiabuahpembeli.ui.home.detail

import com.xenrath.manusiabuahpembeli.data.database.model.DataProduct
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseBargain
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseProductDetail

interface HomeDetailContract {

    interface Presenter {
        fun getDetail(id: Long)
        fun bargainProduct(
            user_id: String,
            product_id: String,
            price: String,
            price_offer: String,
            total_item: String,
            status: String
        )
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoadingDetail(loading: Boolean)
        fun onLoadingBottomSheet(loading: Boolean)
        fun onResultDetail(responseProductDetail: ResponseProductDetail)
        fun onResultBargain(responseBargain: ResponseBargain)
        fun showDialogBuy(dataProduct: DataProduct)
        fun showDialogBargain(dataProduct: DataProduct)
        fun showDialogLocation(dataProduct: DataProduct)
        fun showMessage(message: String)
    }

}