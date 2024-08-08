package com.chronelab.roombasic

import android.app.Application
import com.chronelab.roombasic.database.DatabaseDataContainer
import com.chronelab.roombasic.model.User

class RoomApp: Application(){
    lateinit var dbContainer: DatabaseDataContainer
    var user:User? = null

    override fun onCreate() {
        super.onCreate()
        dbContainer = DatabaseDataContainer(this)
    }

    fun logout() {
        user = null
    }
    fun login(loggedUser: User?) {
        user = loggedUser
    }

    fun loggedUser(): User? {
        return user
    }
}