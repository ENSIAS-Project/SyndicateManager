package com.ensias.syndicatemanager.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.ensias.syndicatemanager.viewmodels.AuthViewModel

@Composable
fun LoginScreen(
    openAndPopUp: (String, String) -> Unit,
    open:(String) -> Unit,
    authViewModel: AuthViewModel = hiltViewModel()
    
){
    val uiState by authViewModel.uiState
    LoginBackground(400,(-40))
    LoginScreenContent(
        state = uiState,
        setName = authViewModel::setname,
        setPass = authViewModel::setpass,
        logIn = {authViewModel.login(openAndPopUp)},
        signupscreen = {authViewModel.signupscreen(open)}
        )
}

@Composable
fun LoginScreenContent(
    state: LoginUiState,
    setName: (String) -> Unit,
    setPass: (String) -> Unit,
    logIn:() -> Unit,
    signupscreen: () -> Unit
){
    // to ensure recomposition everytime uiState changes
    Column(
        modifier = Modifier.fillMaxSize() ,
        verticalArrangement = Arrangement.spacedBy(10.dp,alignment=Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Spacer(modifier = Modifier.padding(150.dp))
        EmailField(value = state.email, setName)
        Spacer(modifier=Modifier.padding(2.dp))//space
        PasswordField(value = state.password,setPass,"mot de pass")
        Spacer(modifier=Modifier.padding(10.dp))//space
        Text(text = "Mot de pass oublié")
        Button(
            onClick = { logIn() },
            modifier = Modifier.width(150.dp),
            colors=ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary)
        )   {

            Text(text = stringResource(R.string.LOGIN_SCREEN_LOGIN_BTN),Modifier.padding(vertical=8.dp),
                color = MaterialTheme.colorScheme.onSecondary)
        }
        Button(
            onClick = { signupscreen() },
            modifier = Modifier.width(150.dp),
            colors=ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )
        )   {
            Text(text = stringResource(R.string.LOGIN_SCREEN_SIGNUP_BTN),Modifier.padding(vertical=8.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer)
        }
        Spacer(modifier=Modifier.padding(2.dp))//space
    }
}



@PreviewLightDark
@Composable
fun LoginPreview(){
    SyndicateManagerTheme {
        LoginBackground(400,(-44))
        val state : LoginUiState= LoginUiState(email = "testemail@example.com", password = "testpassword")
        LoginScreenContent(state,{},{},{}) {}
    }
}