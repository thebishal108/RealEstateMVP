package com.skeleton.mvp.ui.base
interface BaseInterface {


    interface BaseView {
    }

    interface BaseInteractor {

    }

    interface BasePresenter {
        fun onAttach()
        fun onDetach()
    }
}