package com.example.realestateassignment.ui.main

import com.skeleton.mvp.ui.base.BaseInterface

interface MainInterface {
    interface MainView : BaseInterface.BaseView {
//        fun openLoginScreen()
    }
    interface MainPresenter : BaseInterface.BasePresenter {
        fun fetchApiData()
    }
    interface MainInteractor : BaseInterface.BaseInteractor {
//        fun login(email: String, password: String, mApiListener: BaseInterface.ApiListener)
    }
}