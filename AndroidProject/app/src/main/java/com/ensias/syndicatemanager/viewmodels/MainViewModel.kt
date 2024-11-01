package com.ensias.syndicatemanager.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ensias.syndicatemanager.ADD_CONTRIB
import com.ensias.syndicatemanager.ADD_SPENDING
import com.ensias.syndicatemanager.NavDrawerItems
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

    fun onNavDraweElementSelected(
        appState: SyndicateManagerAppState,
        id: Int,
        open: (String) -> Unit
    ) {
        appState.coroutineScope.launch {
            appState.drawerState.close()
        }

        open(NavDrawerItems.opens[NavDrawerItems.items.indexOf(id)])


       // if(id== NavDrawerItems.items[0]){
         //   open(USER_MANAGER)
       // }
       // if(id == NavDrawerItems.items[1]){
          //  open(ADD_SPENDING)
        //}

        //if(id==NavDrawerItems.items[2] ) {
          //  open(ADD_CONTRIB)
        //}


    }
}