package com.wd.kotlindemo.model.constant

import com.wd.kotlindemo.model.bean.LoginBean
import com.wd.kotlindemo.model.bean.RegisterBean
import com.wd.kotlindemo.view.interfaces.IBaseView

/**
@author 2019/12/20
@author 18:21
杨立朝
 */
interface IConstantView {
    interface  ILoginView : IBaseView{
        fun loginSuccess(loginBean: LoginBean)
        fun registerSuccess(registerBean: RegisterBean);
    }

}