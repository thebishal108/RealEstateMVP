package com.example.realestateassignment.ui.main

import android.os.Bundle
import android.view.View
import com.example.realestateassignment.R
import com.skeleton.mvp.ui.base.BaseActivity

class MainActivity : BaseActivity(), MainInterface.MainView {
    private lateinit var mMainPresenter: MainInterface.MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mMainPresenter = MainPresenterImpl(this)
        mMainPresenter.onAttach()
        init()
    }
    private fun init() {
        mMainPresenter.fetchApiData()
    }
    override fun onDestroy() {
        super.onDestroy()
        mMainPresenter.onDetach()
    }
}