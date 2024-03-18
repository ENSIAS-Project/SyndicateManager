package com.ensias.syndicatemanager.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val TAG : String = "FireBaseAccServie"

    fun Login(){
        Log.d(TAG,"login with ${uiState.value.email} and ${uiState.value.password}")
        viewModelScope.launch {  // the recommended way to call suspend function inside viewmodels
            accountService.authenticate(LoginUiModel(uiState.value.email,uiState.value.password)) { throwable ->
                // for testing
                Log.d(TAG,"logincallback activated")
                if (throwable == null) {
                    Log.d(TAG,"login throwable null")
                    uiState.value = uiState.value.copy(logged = true)
                    Log.d(TAG,"${uiState.value.logged}")
                }
            }
        }
    }
    fun Logout(){
        Log.d(TAG,"Logout called")
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