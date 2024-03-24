package com.ensias.syndicatemanager.ui.view


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ensias.syndicatemanager.ui.state.SignUpUiState

import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ensias.syndicatemanager.R
import com.ensias.syndicatemanager.ui.state.LoginUiState

import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import com.ensias.syndicatemanager.ui.view.components.EmailField
import com.ensias.syndicatemanager.ui.view.components.LoginBackground
import com.ensias.syndicatemanager.ui.view.components.PasswordField

@Composable
fun ResetPasswordScreen(){
    LoginBackground(400,(-50))
}

@Composable
fun ResetPasswordScreenContent(
    state: LoginUiState,
    setName: (String) -> Unit,
    setPass: (String) -> Unit,
    signup : () -> Unit

){
    // to ensure recomposition everytime uiState changes
    Column(
        modifier = Modifier.fillMaxSize() ,
        verticalArrangement = Arrangement.spacedBy(10.dp,alignment=Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(value = state.email, setName)
        Spacer(modifier=Modifier.padding(2.dp))//space
        PasswordField(value = state.password,setPass,"nouveau mot de pass")
        Spacer(modifier=Modifier.padding(2.dp))
        PasswordField(value = state.password,setPass,"confirmation mot de pass")
        Spacer(modifier=Modifier.padding(10.dp))


        Button(
            onClick = { signup() },
            modifier = Modifier.width(150.dp),
            colors=ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF))
        )   {
            Text(text = stringResource(R.string.LOGIN_SCREEN_SIGNUP_BTN),Modifier.padding(vertical=8.dp),Color.Black)
        }

        Spacer(modifier=Modifier.padding(5.dp))
    }

}

@PreviewLightDark
@Composable
fun ResetPasswordPreview(){
    SyndicateManagerTheme {
        LoginBackground(400, (-44))
        val state: LoginUiState = LoginUiState("", "")

        ResetPasswordScreenContent(state,{},{},{})
    }}