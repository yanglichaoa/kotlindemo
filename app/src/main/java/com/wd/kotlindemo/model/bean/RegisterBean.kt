package com.wd.kotlindemo.model.bean

/**
@author 2019/12/20
@author 20:45
杨立朝
 */
class RegisterBean {


    /**
     * message : 注册成功
     * status : 0000
     */

    private var message: String? = null
    private var status: String? = null

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String) {
        this.status = status
    }
}