package com.xenrath.manusiabuahpembeli.ui.bargain.tabs.accepted

import com.xenrath.manusiabuahpembeli.data.database.model.ResponseBargainList

interface AcceptedContract {

    interface Presenter {
        fun getBargainAccepted(
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