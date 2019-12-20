package com.wd.kotlindemo.presenter

import com.wd.kotlindemo.view.interfaces.IBaseView

/**
@author 2019/12/20
@author 18:22
杨立朝
 */
open class BasePresenter <V : IBaseView> {
    var mView: V? = null

    fun attachView(v: V) {
        this.mView = v
    }

    fun detachView() {
        this.mView = null
    }

    fun getView(): V? {
        return mView
    }
}