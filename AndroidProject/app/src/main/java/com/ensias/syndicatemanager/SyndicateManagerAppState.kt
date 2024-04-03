package com.ensias.syndicatemanager

import android.content.res.Resources
import androidx.compose.material3.DrawerState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.ensias.syndicatemanager.ui.view.SnackbarManager
import com.ensias.syndicatemanager.ui.view.SnackbarMessage.Companion.toMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class SyndicateManagerAppState(
    val navController: NavHostController,
    private val snackbarManager: SnackbarManager,
    private val resources: Resources,
    val snackbarHostState: SnackbarHostState,
    val drawerState: DrawerState,
    val logged: MutableState<Boolean>,
    val isAdmin: MutableState<Boolean>,
    coroutineScope: CoroutineScope
    ){
    init {
        coroutineScope.launch {
            snackbarManager.snackbarMessages.filterNotNull().collect { snackbarMessage ->
                val text = snackbarMessage.toMessage(resources)
                //scaffoldState.snackbarHostState.showSnackbar(text)
                snackbarHostState.showSnackbar(text)
                snackbarManager.clearSnackbarState()
            }
        }
    }
    fun popUp() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route) { launchSingleTop = true }
    }

    fun navigateAndPopUp(route: String, popUp: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }
    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }

}