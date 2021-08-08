package com.xenrath.manusiabuahpembeli.ui.bargain.detail

import com.xenrath.manusiabuahpembeli.data.database.model.ResponseBargainDetail
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseBargainUpdate

interface BargainDetailContract {

    interface Presenter {
        fun getBargainDetail(id: Long)
        fun updateBargainStatus(
            id: Long,
            status: String
        )
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoadingGet(loading: Boolean)
        fun onLoadingAction(loading: Boolean)
        fun onResult(responseBargainDetail: ResponseBargainDetail)
        fun onResultUpdate(responseBargainUpdate: ResponseBargainUpdate)
        fun showMessage(message: String)
    }

}