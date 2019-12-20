package com.wd.kotlindemo.model.bean

/**
@author 2019/12/20
@author 18:02
杨立朝
 */
class LoginBean {
    /**
     * result : {"nickName":"徐云杰","phone":"18600151568","pwd":"R+0jdN3P4MXHPMFVe9cX5MbX5ulIXHJkfigPLKEeTBY5lUgxJWUNg0js1oGtbsKiLFL4ScqdmUbtHXIfrgQnWrwTNjf09OJLycbeJ+ka4+CV7I1eEqG8DtZPnQoCyxjoYMjO4soDl6EX9YgqaZp3DlUH4pXrYHYz58YyFkSeJEk=","sessionId":"15372410025241007","userId":1007,"userName":"4Vg15Z18600151568","whetherVip":2,"whetherFaceId":1}
     * message : 登录成功
     * status : 0000
     */

    private var result: ResultBean? = null
    private var message: String? = null
    private var status: String? = null

    fun getResult(): ResultBean? {
        return result
    }

    fun setResult(result: ResultBean) {
        this.result = result
    }

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

    class ResultBean {
        /**
         * nickName : 徐云杰
         * phone : 18600151568
         * pwd : R+0jdN3P4MXHPMFVe9cX5MbX5ulIXHJkfigPLKEeTBY5lUgxJWUNg0js1oGtbsKiLFL4ScqdmUbtHXIfrgQnWrwTNjf09OJLycbeJ+ka4+CV7I1eEqG8DtZPnQoCyxjoYMjO4soDl6EX9YgqaZp3DlUH4pXrYHYz58YyFkSeJEk=
         * sessionId : 15372410025241007
         * userId : 1007
         * userName : 4Vg15Z18600151568
         * whetherVip : 2
         * whetherFaceId : 1
         */

        var nickName: String? = null
        var phone: String? = null
        var pwd: String? = null
        var sessionId: String? = null
        var userId: Int = 0
        var userName: String? = null
        var whetherVip: Int = 0
        var whetherFaceId: Int = 0
    }
}