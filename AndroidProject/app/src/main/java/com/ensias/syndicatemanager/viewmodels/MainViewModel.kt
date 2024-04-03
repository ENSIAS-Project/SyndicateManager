package com.ensias.syndicatemanager.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ensias.syndicatemanager.SyndicateManagerAppState
import com.ensias.syndicatemanager.di.Repo
import com.ensias.syndicatemanager.models.User
import com.ensias.syndicatemanager.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class MainViewModel @Inject constructor(
    private val accountService : AccountService,
): ViewModel(){
    fun logout(){
        viewModelScope.launch {
            accountService.logout()
            Repo.logged=false
            Repo.user = User()

        }
    }
}