package com.xenrath.manusiabuahpembeli.ui.bargain

class BargainPresenter(val view: BargainContract.View) {

    init {
        view.initActivity()
        view.initListener()
    }

}