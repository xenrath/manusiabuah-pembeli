package com.xenrath.manusiabuahpembeli.ui

class MainPresenter(val view: MainContract.View) {

    init {
        view.initListener()
    }

}