package com.wd.kotlindemo.model.okhttp

import com.wd.kotlindemo.model.api.Api
import com.wd.kotlindemo.model.bean.LoginBean
import com.wd.kotlindemo.model.bean.RegisterBean
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.Observer
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.Exception
import java.util.concurrent.TimeUnit

/**
@author 2019/12/20
@author 18:04
杨立朝
 */
class OkHttpUtils private constructor(){
    var  iApi:Api?=null

    //单例模式
    companion object {
        val instance : OkHttpUtils by lazy (mode=LazyThreadSafetyMode.SYNCHRONIZED ){
            OkHttpUtils()
        }
    }

     init {

         //拦截器
         val httpLoggingInterceptor = HttpLoggingInterceptor()
          httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


         val okHttpClient = OkHttpClient.Builder()
             .addInterceptor(httpLoggingInterceptor)
             .readTimeout(5, TimeUnit.SECONDS)
             .writeTimeout(5, TimeUnit.SECONDS)
             .build()


         val retrofit:Retrofit = Retrofit.Builder()
             .client(okHttpClient)
             .baseUrl("https://mobile.bwstudent.com/")
             .addConverterFactory(GsonConverterFactory.create())
             .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
             .build()


        iApi = retrofit.create(Api::class.java)

     }

    fun login(iokClickBack: IOKClickBack,phone:String,pwd:String){
        val login = iApi!!.login(phone, pwd)
        login.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :Observer<LoginBean>{
                override fun onCompleted() {
                }

                override fun onError(e: Throwable?) {
                }

                override fun onNext(loginBean: LoginBean) {
                    try {
                        iokClickBack!!.iokclickback(loginBean)
                    }catch (e:Exception){
                        e.printStackTrace()
                    }

                }

            })
    }


    fun register(registerClickBack: RegisterClickBack,phone:String,nickName:String,pwd: String){
        val register = iApi!!.register(phone, nickName, pwd)
         register.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
             .subscribe(object  : Observer<RegisterBean>{
                 override fun onError(e: Throwable?) {

                 }

                 override fun onNext(registerBean: RegisterBean) {
                     try {
                         registerClickBack!!.registerClickBack(registerBean)
                     }catch (e:Exception){
                         e.printStackTrace()
                     }
                 }

                 override fun onCompleted() {
                 }

             })
    }


    open interface IOKClickBack{
         fun  iokclickback(loginBean: LoginBean)
    }
    open interface RegisterClickBack{
        fun  registerClickBack(registerBean: RegisterBean)
    }


}

private fun Any.subscribe(observer: Observer<RegisterBean>) {

}


