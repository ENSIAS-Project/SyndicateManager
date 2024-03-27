package com.ensias.syndicatemanager.ui.view


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.ensias.syndicatemanager.ui.state.ResetUiState

import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import com.ensias.syndicatemanager.ui.view.components.EmailField
import com.ensias.syndicatemanager.ui.view.components.LoginBackground
import com.ensias.syndicatemanager.ui.view.components.PasswordField
import com.ensias.syndicatemanager.viewmodels.AuthViewModel

@Composable
fun ResetPasswordScreen(
    openAndPopUp: (String, String) -> Unit,
    open:(String) -> Unit,
    authViewModel: AuthViewModel = hiltViewModel()
){
    val uiState by authViewModel.resetUiState
    ResetPasswordScreenContent(
        state = uiState,
        setEmail = authViewModel::resetSetEmail ,
        onEmailValidation = authViewModel::onResetEmailValidation,
        resetPassword = {authViewModel.resetPassword(openAndPopUp)}
    )
}

@Composable
fun ResetPasswordScreenContent(
    state: ResetUiState,
    setEmail: (String) -> Unit,
    onEmailValidation:(Boolean) -> Unit,
    resetPassword : () -> Unit

){
    // to ensure recomposition everytime uiState changes
    Column(
        modifier = Modifier.fillMaxSize() ,
        verticalArrangement = Arrangement.spacedBy(10.dp,alignment=Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(value = state.email, valid = state.isMailValid, onValidation =onEmailValidation , onNewValue = setEmail )
        Spacer(modifier=Modifier.padding(10.dp))
        Button(
            onClick = { resetPassword() },
            modifier = Modifier.width(250.dp),
            colors=ButtonDefaults.buttonColors(MaterialTheme.colorScheme.errorContainer)
        )   {
            Text(text = stringResource(R.string.RESET_PASSWORD),Modifier.padding(vertical=8.dp),MaterialTheme.colorScheme.onErrorContainer)
        }
        Spacer(modifier=Modifier.padding(10.dp))

    }
}

@PreviewLightDark
@Composable
fun ResetPasswordPreview(){
    SyndicateManagerTheme {
        LoginBackground(400, (-44))
        val state: ResetUiState = ResetUiState(email = "testemail@mail.com",isMailValid = false)
        ResetPasswordScreenContent(state,{},{},{})
    }}