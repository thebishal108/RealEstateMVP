package com.skeleton.mvp.ui.base
open class BasePresenterImpl : BaseInterface.BasePresenter {
    var isViewAttached: Boolean = false

    override fun onAttach() {
        isViewAttached = true
    }

    override fun onDetach() {
        isViewAttached = false
    }
}
