package com.ensias.syndicatemanager.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ensias.syndicatemanager.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
    lvm: LoginViewModel = hiltViewModel()
){
    Column(
        modifier = Modifier.fillMaxSize() ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = lvm.name,
            label = { Text(text = "Email")},
            onValueChange = {lvm.name = it}
        )
        OutlinedTextField(value = lvm.pass,
            label = { Text(text = "password")},
            onValueChange = {lvm.pass = it}
        )
        Button(
            onClick = { lvm.Login() }
        ) {
            Text(text = "login")
        }
    }
}