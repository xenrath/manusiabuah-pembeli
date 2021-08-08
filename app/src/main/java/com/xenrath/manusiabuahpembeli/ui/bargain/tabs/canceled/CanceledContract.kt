package com.xenrath.manusiabuahpembeli.ui.bargain.tabs.canceled

import com.xenrath.manusiabuahpembeli.data.database.model.ResponseBargainList

interface CanceledContract {

    interface Presenter {
        fun getBargainCanceled(
            user_id: String,
            status: String
        )
    }

    interface View {
        fun initFragment(view: android.view.View)
        fun onLoading(loading: Boolean)
        fun onResult(responseBargainList: ResponseBargainList)
    }

}