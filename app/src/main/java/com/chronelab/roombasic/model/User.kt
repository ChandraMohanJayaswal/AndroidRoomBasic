package com.chronelab.roombasic.model

import java.io.Serializable

data class User(val id:Int, var firstName: String, var lastName:String, var userName: String, var password: String):
    Serializable {

    fun validate(): Pair<Boolean, String> {
        var message = ""
        var status = true
        if (userName != "user") {
            status = false
            message = "Invalid user name."
        } else if (password != "password") {
            status = false
            message = "Invalid password."
        }
        return Pair(status, message)
    }
}