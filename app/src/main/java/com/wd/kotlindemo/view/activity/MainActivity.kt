package com.wd.kotlindemo.view.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
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
import kotlinx.android.synthetic.main.activity_main.view.*
import java.lang.Exception


class MainActivity : BaseActivity(),IConstantView.ILoginView{

    //注册
    override fun registerSuccess(registerBean: RegisterBean) {
    }

    lateinit var loginPresenter: LoginPresenter

    //登陆
    override fun loginSuccess(loginBean: LoginBean) {
        if (loginBean.getStatus().equals("0000")){
            loginBean.getMessage()!!.toast(this,Toast.LENGTH_SHORT)
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
        }else{
            loginBean.getMessage()!!.toast(this,Toast.LENGTH_SHORT)
        }
    }

    override fun bindLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        zhuce.setOnClickListener(object  : View.OnClickListener{
            override fun onClick(v: View) {
              activityTiaozhuan(this@MainActivity,RegisterActivity().javaClass)
            }
        })
        denglu.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                var phone = phone.text.toString().trim()
                var pwd = pwd.text.toString().trim()
                if (TextUtils.isEmpty(phone)){
                    "手机号不能为空".toast(this@MainActivity,Toast.LENGTH_SHORT)
                }
                if (TextUtils.isEmpty(pwd)){
                    "密码不能为空".toast(this@MainActivity,Toast.LENGTH_SHORT)
                }
                try {
                    var encryptPwd = RsaCoder.encryptByPublicKey(pwd)
                    Log.i("encryptPwd",encryptPwd)
                    loginPresenter.login(phone,encryptPwd)
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

    //intent跳转封装
    fun activityTiaozhuan(ctx: Context,clazz:Class<Any>){
        var intent = Intent()
        intent.setClass(ctx,clazz)
        startActivity(intent)
    }


    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
        return Toast.makeText(context, this.toString(), duration).apply { show() }
    }

    override fun onDestroy() {
        super.onDestroy()
        loginPresenter.detachView()
    }
}
