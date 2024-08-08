package com.chronelab.roombasic.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.chronelab.roombasic.model.User
import com.chronelab.roombasic.ui.theme.RoomBasicTheme
import com.chronelab.roombasic.ui.view.ViewLogin


class LoginActivity : ComponentActivity() {
    companion object {
        private  val TAG = LoginActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoomBasicTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ViewLogin { userName, password ->
                        val user = User(id = 1, userName = userName, password = password, firstName = "Chandra", lastName = "Jayaswal")
                        btnLoginAction(user)
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.i(TAG,"Handle any updates or UI changes needed here" )
    }

    private fun btnLoginAction(user: User): Pair<Boolean, String> {
        val pairMessage = user.validate()
        if (pairMessage.first) {
            val roomApp = application as com.chronelab.roombasic.RoomApp
            roomApp.login(user)
            var intent = Intent(this, com.chronelab.roombasic.activity.HomeActivity::class.java)
            startActivity(intent)
        } else {
            Log.i(TAG, "You are not valid user!")
        }
        return pairMessage
    }
}

