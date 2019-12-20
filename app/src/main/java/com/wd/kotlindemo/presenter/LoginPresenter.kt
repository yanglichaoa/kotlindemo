package com.wd.kotlindemo.presenter

import com.wd.kotlindemo.model.bean.LoginBean
import com.wd.kotlindemo.model.bean.RegisterBean
import com.wd.kotlindemo.model.constant.IConstantView
import com.wd.kotlindemo.model.okhttp.OkHttpUtils
import okhttp3.OkHttpClient

/**
@author 2019/12/20
@author 18:23
杨立朝
 */
class LoginPresenter : BasePresenter<IConstantView.ILoginView>() {
    fun  login(phone:String,pwd:String){
         OkHttpUtils.instance.login(object : OkHttpUtils.IOKClickBack{
             override fun iokclickback(loginBean: LoginBean) {
                getView()!!.loginSuccess(loginBean)
             }
         },phone,pwd)
    }


    fun  register(phone:String,nickName:String,pwd:String){
        OkHttpUtils.instance.register(object : OkHttpUtils.RegisterClickBack{
            override fun registerClickBack(registerBean: RegisterBean) {
              getView()!!.registerSuccess(registerBean)
            }
        },phone,nickName,pwd)
    }
}