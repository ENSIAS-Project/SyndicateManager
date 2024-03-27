package com.ensias.syndicatemanager.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ensias.syndicatemanager.AUTH
import com.ensias.syndicatemanager.LOGIN
import com.ensias.syndicatemanager.MAIN
import com.ensias.syndicatemanager.R
import com.ensias.syndicatemanager.RESET_PASSWORD
import com.ensias.syndicatemanager.SIGNUP
import com.ensias.syndicatemanager.di.Repo
import com.ensias.syndicatemanager.exceptions.AuthException
import com.ensias.syndicatemanager.exceptions.impl.RegisterPasswordMismatchException
import com.ensias.syndicatemanager.models.LoginUiModel
import com.ensias.syndicatemanager.models.RegisterUiModel
import com.ensias.syndicatemanager.models.User
import com.ensias.syndicatemanager.service.AccountService
import com.ensias.syndicatemanager.ui.state.LoginUiState
import com.ensias.syndicatemanager.ui.state.RegisterUiState
import com.ensias.syndicatemanager.ui.state.ResetUiState
import com.ensias.syndicatemanager.ui.view.SnackbarManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel  @Inject constructor(
    private val accountService : AccountService,
    ) :ViewModel(){
    var loginUistate = mutableStateOf(LoginUiState())
    private set
    var registerUistate = mutableStateOf(RegisterUiState())
        private set
    var resetUiState = mutableStateOf(ResetUiState())
    private val TAG : String = "LoginviewModel"

    fun login(openAndPopUp: (String, String) -> Unit){
        Log.d(TAG,"login with ${loginUistate.value.email} and ${loginUistate.value.password}")
        loginUistate.value = loginUistate.value.copy(logging = true)
        viewModelScope.launch {  // the recommended way to call suspend function inside viewmodels
            try {
                accountService.authenticate(LoginUiModel(loginUistate.value.email,loginUistate.value.password)) {
                    setUser(it)
                    openAndPopUp(MAIN, LOGIN)
                }

            } catch (e: AuthException){
                loginExceptionHandler(e)
                loginUistate.value = loginUistate.value.copy(logging = true)

            }
        }
    }


    private fun loginExceptionHandler(e: AuthException) {
        SnackbarManager.showMessage(e.getmessage())
    }
    private fun setUser(user: User){
        // User successsfully logged
        Repo.user = user // set it on the repo
        Repo.logged = true
    }

    fun logout(){
        viewModelScope.launch {
            accountService.logout()
            Repo.logged=false
        }
    }

    fun signupscreen(open: ( String) -> Unit){
        open(SIGNUP)
    }

    fun setLoginEmail(newemail:String){
        loginUistate.value = loginUistate.value.copy(email = newemail)
    }
    fun setLoginPassword(newpass:String){
        loginUistate.value = loginUistate.value.copy(password = newpass)
    }

    fun onLoginEmailValidation(valid: Boolean) {
        loginUistate.value = loginUistate.value.copy(validmail = valid)
    }

    fun register(openAndPopUp: (String, String) -> Unit) {
        Log.d(TAG,"register with ${registerUistate.value.email} and ${registerUistate.value.password}")
        if(! (registerUistate.value.password.equals(registerUistate.value.confirmpass))){
            // passwords mismatch
            // notify the user via snackbar
            SnackbarManager.showMessage(RegisterPasswordMismatchException())
            return // don't proceed via registration
        }
        Repo.user = User(
            email = registerUistate.value.email,
            IS_ADMIN = false,
            name = registerUistate.value.prenom,
            familyname = registerUistate.value.nom
        )
        viewModelScope.launch {  // the recommended way to call suspend function inside viewmodels
            try { //Re(registerUistate.value.email,registerUistate.value.password)
                accountService.Register(RegisterUiModel(
                    nom =       registerUistate.value.nom,
                    prenom =    registerUistate.value.prenom,
                    password =  registerUistate.value.password,
                    email =     registerUistate.value.email
                )) {
                    setUser(it)
                    openAndPopUp(MAIN, LOGIN)
                }
            } catch (e: AuthException){ loginExceptionHandler(e) }
        }
    }

    fun setRegisterName(s: String) {
        registerUistate.value = registerUistate.value.copy(nom = s)
    }

    fun setFamilyname(s: String) {
        registerUistate.value = registerUistate.value.copy(prenom = s)
    }

    fun setEmail(s: String) {
        registerUistate.value = registerUistate.value.copy(email = s)
    }

    fun setRegisterPass(s: String) {
        registerUistate.value = registerUistate.value.copy(password = s)
    }

    fun setVerificationPass(s: String) {
        registerUistate.value = registerUistate.value.copy(confirmpass = s)
    }

    fun onRegisterEmailValidation(valid: Boolean) {
        registerUistate.value = registerUistate.value.copy(validmail = valid)
    }

    fun resetSetEmail(s: String) {
        resetUiState.value = resetUiState.value.copy(email = s)
    }

    fun resetPassword(openAndPopUp: (String, String) -> Unit) {
        accountService.reset(resetUiState.value.email) {
            SnackbarManager.showMessage(R.string.EMAIL_RESET_SNACK_BAR_MESSAGE)
            openAndPopUp(AUTH, LOGIN)
        }
    }

    fun onResetEmailValidation(b: Boolean) {
        resetUiState.value = resetUiState.value.copy(isMailValid = b)
    }

    fun resetPasswordScreen(open: (String) -> Unit) {
        open(RESET_PASSWORD)
    }


}