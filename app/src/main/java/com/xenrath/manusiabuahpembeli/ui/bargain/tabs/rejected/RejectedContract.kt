package com.xenrath.manusiabuahpembeli.ui.bargain.tabs.rejected

import com.xenrath.manusiabuahpembeli.data.database.model.ResponseBargainList

interface RejectedContract {

    interface Presenter {
        fun getBargainRejected(
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