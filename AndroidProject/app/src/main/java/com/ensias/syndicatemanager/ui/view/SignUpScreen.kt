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
import androidx.compose.runtime.getValue
import com.ensias.syndicatemanager.ui.state.RegisterUiState

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ensias.syndicatemanager.R

import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import com.ensias.syndicatemanager.ui.view.components.EmailField
import com.ensias.syndicatemanager.ui.view.components.LoginBackground
import com.ensias.syndicatemanager.ui.view.components.NomField
import com.ensias.syndicatemanager.ui.view.components.PasswordField
import com.ensias.syndicatemanager.ui.view.components.PrenomField
import com.ensias.syndicatemanager.viewmodels.AuthViewModel

@Composable
fun SignUpScreen(
    openAndPopUp: (String, String) -> Unit,
    open:(String) -> Unit,
    authViewModel: AuthViewModel = hiltViewModel()
    ){
    val uiState by authViewModel.registerUistate

    RegisterScreenContent(state = uiState,
        setPrenom = authViewModel::setFamilyname,
        setNom = authViewModel::setRegisterName ,
        setPass = authViewModel::setRegisterPass,
        setVerifPass = authViewModel::setVerificationPass,
        setMail = authViewModel::setEmail,
        onValidMail = {valid -> authViewModel.onRegisterEmailValidation(valid)},
        register = {authViewModel.register(openAndPopUp)}
    )
}

@Composable
fun RegisterScreenContent(
    state: RegisterUiState,
    setPrenom: (String) -> Unit,
    setNom:(String)->Unit,
    setPass: (String) -> Unit,
    setMail:(String) -> Unit,
    setVerifPass: (String) -> Unit,
    onValidMail:(Boolean) -> Unit,
    register : () -> Unit

){
    // to ensure recomposition everytime uiState changes
    Column(
        modifier = Modifier.fillMaxSize() ,
        verticalArrangement = Arrangement.spacedBy(10.dp,alignment=Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NomField(value = state.nom,setNom)
        Spacer(modifier=Modifier.padding(2.dp))//space
        PrenomField(value = state.prenom, setPrenom)
        Spacer(modifier=Modifier.padding(2.dp))
        EmailField(value = state.email, valid = state.validmail, onValidation = onValidMail, onNewValue = setMail)
        Spacer(modifier=Modifier.padding(2.dp))
        PasswordField(value = state.password,setPass,stringResource(id = R.string.PASSWORD))
        Spacer(modifier=Modifier.padding(2.dp))
        PasswordField(value = state.confirmpass,setVerifPass,stringResource(id = R.string.RETYPE_PASSWORD))
        Spacer(modifier=Modifier.padding(5.dp))
        Button(
            onClick = { register() },
            modifier = Modifier.width(150.dp),
            colors=ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)) // FIXME : avoid using colors outside of the theme
        )   {
            Text(text = stringResource(R.string.LOGIN_SCREEN_SIGNUP_BTN),Modifier.padding(vertical=8.dp),Color.Black)
        }

        Spacer(modifier=Modifier.padding(5.dp))
        }

    }

@PreviewLightDark
@Composable
fun RegisterPreviewPreview(){
    SyndicateManagerTheme {
        LoginBackground(460,(-60))
        val state : RegisterUiState=RegisterUiState("nisrine","Bakhouche","")
        RegisterScreenContent(state,{},{},{},{},{},{},{})
    }}