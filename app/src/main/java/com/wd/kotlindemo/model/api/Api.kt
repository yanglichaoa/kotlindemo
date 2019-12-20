package com.wd.kotlindemo.model.api

import com.wd.kotlindemo.model.bean.LoginBean
import com.wd.kotlindemo.model.bean.RegisterBean
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Observable

/**
@author 2019/12/20
@author 17:56
杨立朝
 */
interface Api {
    @POST("techApi/user/v1/login")
    fun login(@Query("phone")phone:String,@Query("pwd")pwd:String):Observable<LoginBean>


    @POST("techApi/user/v1/register")
    fun register(@Query("phone")phone:String,@Query("nickName")nickName:String,@Query("pwd")pwd:String):Observable<RegisterBean>
}