package com.ensias.syndicatemanager.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ensias.syndicatemanager.LOGIN
import com.ensias.syndicatemanager.MAIN
import com.ensias.syndicatemanager.SIGNUP
import com.ensias.syndicatemanager.di.Repo
import com.ensias.syndicatemanager.exceptions.AuthException
import com.ensias.syndicatemanager.models.LoginUiModel
import com.ensias.syndicatemanager.models.User
import com.ensias.syndicatemanager.service.AccountService
import com.ensias.syndicatemanager.ui.state.LoginUiState
import com.ensias.syndicatemanager.ui.view.SnackbarManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel  @Inject constructor(
    private val accountService : AccountService,
    ) :ViewModel(){
    var uiState = mutableStateOf(LoginUiState())
    private set
    private val TAG : String = "LoginviewModel"

    fun login(openAndPopUp: (String, String) -> Unit){
        Log.d(TAG,"login with ${uiState.value.email} and ${uiState.value.password}")
        viewModelScope.launch {  // the recommended way to call suspend function inside viewmodels
            try {
                accountService.authenticate(LoginUiModel(uiState.value.email,uiState.value.password)) {
                    setUser(it)
                    openAndPopUp(MAIN, LOGIN)
                }

            } catch (e: AuthException){
                loginExceptionHandler(e)

            }
        }
    }


    private fun loginExceptionHandler(e: AuthException) {
        SnackbarManager.showMessage(e.getmessage())
    }
    private fun setUser(user: User){
        // User successsfully logged
        Repo.user = user // set it on th repo
    }

    fun logout(){
        viewModelScope.launch {
            accountService.logout()
            uiState.value =uiState.value.copy(logged = false)
        }
    }

    fun signupscreen(open: ( String) -> Unit){
        open(SIGNUP)
    }

    fun setname(newemail:String){
        uiState.value = uiState.value.copy(email = newemail)
    }
    fun setpass(newpass:String){
        uiState.value = uiState.value.copy(password = newpass)
    }

    fun signup(openAndPopUp: (String, String) -> Unit) {
        Log.d(TAG,"signup with ${uiState.value.email} and ${uiState.value.password}")
        Repo.user = User(Email = uiState.value.email, name = "TO_BE_IMPLEMENTED", familyname = "TO_BE_IMPLEMENTED,")
        viewModelScope.launch {  // the recommended way to call suspend function inside viewmodels
            try {
                accountService.SignUp(LoginUiModel(uiState.value.email,uiState.value.password)) {
                    setUser(it)
                    openAndPopUp(MAIN, LOGIN)
                }

            } catch (e: AuthException){
                loginExceptionHandler(e)

            }
        }
    }

}