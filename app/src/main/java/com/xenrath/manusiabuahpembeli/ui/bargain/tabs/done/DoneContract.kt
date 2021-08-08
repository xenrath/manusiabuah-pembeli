package com.xenrath.manusiabuahpembeli.ui.bargain.tabs.done

import com.xenrath.manusiabuahpembeli.data.database.model.ResponseBargainList

interface DoneContract {

    interface Presenter {
        fun getBargainDone(
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