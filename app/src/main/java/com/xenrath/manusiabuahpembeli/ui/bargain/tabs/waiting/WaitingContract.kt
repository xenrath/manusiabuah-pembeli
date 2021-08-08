package com.xenrath.manusiabuahpembeli.ui.bargain.tabs.waiting

import com.xenrath.manusiabuahpembeli.data.database.model.ResponseBargainList

interface WaitingContract {

    interface Presenter {
        fun getBargainWaiting(
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