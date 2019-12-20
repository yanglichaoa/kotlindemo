package com.wd.kotlindemo.view.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.wd.health.kotlinlogin.view.activity.BaseActivity
import com.wd.kotlindemo.R
import com.wd.kotlindemo.model.bean.LoginBean
import com.wd.kotlindemo.model.bean.RegisterBean
import com.wd.kotlindemo.model.constant.IConstantView
import com.wd.kotlindemo.model.okhttp.RsaCoder
import com.wd.kotlindemo.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import java.io.File

class RegisterActivity : BaseActivity(),IConstantView.ILoginView{


    lateinit var loginPresenter: LoginPresenter
    override fun loginSuccess(loginBean: LoginBean) {

    }

    override fun registerSuccess(registerBean: RegisterBean) {
        if (registerBean.getStatus().equals("0000")){
            registerBean.getMessage()!!.toast(this,Toast.LENGTH_SHORT)
            finish()
        }else{
            registerBean.getMessage()!!.toast(this,Toast.LENGTH_SHORT)
        }
    }

    override fun bindLayout(): Int {
        return R.layout.activity_register
    }

    override fun initView() {
        zhuce1.setOnClickListener(object  :View.OnClickListener{
            override fun onClick(v: View?) {

                var phone = phone1.text.toString().trim()
                var pwd = pwd1.text.toString().trim()
                var nickname = nickname.text.toString().trim()
                if (TextUtils.isEmpty(phone)){
                    "手机号不能为空".toast(this@RegisterActivity, Toast.LENGTH_SHORT)
                    return
                }
                if (TextUtils.isEmpty(nickname)){
                    "用户名不能为空".toast(this@RegisterActivity,Toast.LENGTH_SHORT)
                    return
                }
                if (TextUtils.isEmpty(pwd)){
                    "密码不能为空".toast(this@RegisterActivity,Toast.LENGTH_SHORT)
                    return
                }
                try {
                    var encryptPwd = RsaCoder.encryptByPublicKey(pwd)
                    Log.i("encryptPwd",encryptPwd)
                    loginPresenter.register(phone,nickname,encryptPwd)
                }catch (e : Exception){
                    e.printStackTrace()
                }
            }
        })
    }

    override fun initData() {
        loginPresenter = LoginPresenter()
        loginPresenter.attachView(this)
    }


    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
        return Toast.makeText(context, this.toString(), duration).apply { show() }
    }

    override fun onDestroy() {
        super.onDestroy()
        loginPresenter.detachView()
    }
}
