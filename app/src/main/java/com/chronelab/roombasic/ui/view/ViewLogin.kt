package com.chronelab.roombasic.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chronelab.roombasic.R
import com.chronelab.roombasic.ui.theme.RoomBasicTheme
import com.chronelab.roombasic.ui.view.header.LoginHeader
import com.chronelab.roombasic.ui.view.util.ViewAlert

@Composable
fun ViewLogin(validateUser:(userName: String, password: String)->Pair<Boolean, String>) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var userValidationPair by remember { mutableStateOf(Pair(true, ""))}

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()
    ) {
        LoginHeader(title = stringResource(id = R.string.txt_login))
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .imePadding()
            ) {
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text(stringResource(id = R.string.txt_username)) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(stringResource(id = R.string.txt_password)) },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        userValidationPair =  validateUser(username, password)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !(username == "" || password == "")
                ) {
                    Text(text = stringResource(id = R.string.txt_login))
                }

                if (!userValidationPair.first) {
                    ViewAlert(
                        message = userValidationPair.second,
                        dismissButtonText = "OK",
                        dismissButtonAction = {
                            userValidationPair = Pair(!userValidationPair.first, userValidationPair.second)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ViewLoginPreview() {
    RoomBasicTheme {
        ViewLogin { userName, password ->
            Pair(true, "")
        }
    }
}