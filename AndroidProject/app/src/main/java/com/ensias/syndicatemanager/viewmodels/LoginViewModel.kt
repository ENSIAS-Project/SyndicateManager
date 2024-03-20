package com.ensias.syndicatemanager.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ensias.syndicatemanager.Exceptions.AuthException
import com.ensias.syndicatemanager.models.LoginUiModel
import com.ensias.syndicatemanager.service.AccountService
import com.ensias.syndicatemanager.ui.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel  @Inject constructor(
    private val accountService : AccountService
) :ViewModel(){
    var uiState = mutableStateOf(LoginUiState())
    private set
    private val TAG : String = "LoginviewModel"

    fun Login(){
        Log.d(TAG,"login with ${uiState.value.email} and ${uiState.value.password}")
        viewModelScope.launch {  // the recommended way to call suspend function inside viewmodels
            accountService.authenticate(LoginUiModel(uiState.value.email,uiState.value.password)) { LoginExceptionHandler(it) }
        }
    }

    fun LoginExceptionHandler(e: AuthException?) {
        if (e == null) {
            uiState.value = uiState.value.copy(logged = true)   // TODO: IMPLEMENT APP STATE
            Log.d(TAG,"${uiState.value.logged}")
        }else{
            if(e == AuthException.INVALID_CREDENTIAL) {         // TODO: IMPLEMENT SnackBar Manager and notify it
                Log.d(TAG, "invalid credential given")
            }
            if(e == AuthException.OTHER_ERROR){
                Log.d(TAG, "ERROR OCCURED")
            }
        }
    }

    fun Logout(){
        viewModelScope.launch {
            accountService.logout()
            uiState.value =uiState.value.copy(logged = false)
        }
    }
    fun setname(newemail:String){
        uiState.value = uiState.value.copy(email = newemail)
    }
    fun setpass(newpass:String){
        uiState.value = uiState.value.copy(password = newpass)
    }

}