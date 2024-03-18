package com.ensias.syndicatemanager.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ensias.syndicatemanager.ui.view.components.EmailField
import com.ensias.syndicatemanager.ui.view.components.PasswordField
import com.ensias.syndicatemanager.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
    lvm: LoginViewModel = hiltViewModel()
){
    // to ensure recomposition everytime uiState changes
    val uiState by lvm.uiState
    Column(
        modifier = Modifier.fillMaxSize() ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(value = uiState.email, lvm::setname)
        PasswordField(value = uiState.password,lvm::setpass)
        if (uiState.logged){
            Button(
                onClick = { lvm.Logout() }
            )   {
                Text(text = "logout")
            }
        }else{
            Button(
                onClick = { lvm.Login() }
            )   {
                Text(text = "login")
            }
        }

        }
    }
