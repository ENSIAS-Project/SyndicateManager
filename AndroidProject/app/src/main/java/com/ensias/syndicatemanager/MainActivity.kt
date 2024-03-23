    package com.ensias.syndicatemanager

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.ensias.syndicatemanager.di.Repo
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import com.ensias.syndicatemanager.ui.view.LoginScreen
import com.ensias.syndicatemanager.ui.view.RegisterScreen
import com.ensias.syndicatemanager.ui.view.SnackbarManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope

    @AndroidEntryPoint
    class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           SyndicateManagerTheme {
               Surface(color = MaterialTheme.colorScheme.background) {
                   val appState = rememberAppState()
                   Scaffold(
                       snackbarHost = {
                           SnackbarHost(
                               hostState = appState.snackbarHostState,
                               modifier = Modifier.padding(8.dp),
                               snackbar = { snackbarData ->
                                   Snackbar(snackbarData, contentColor = MaterialTheme.colorScheme.onPrimary)
                               }
                           )
                       },
                   ) { innerPaddingModifier ->
                       NavHost(
                           navController = appState.navController,
                           startDestination = AUTH,
                           modifier = Modifier.padding(innerPaddingModifier)
                       ) {
                           appGraph(appState)
                       }
                   }
               }
            }
        }
    }
}
@Composable
inline fun<reified T: ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController):T{
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this){
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

    @Composable
    fun rememberAppState(
        navController: NavHostController = rememberNavController(),
        snackbarManager: SnackbarManager = SnackbarManager,
        resources: Resources = resources(),
        snackbarHostState: SnackbarHostState = SnackbarHostState(),
        coroutineScope: CoroutineScope = rememberCoroutineScope()
    ) =
        remember( navController, snackbarManager, resources, snackbarHostState,coroutineScope) {
            SyndicateManagerAppState(navController, snackbarManager, resources, snackbarHostState,coroutineScope)
        }

    @Composable
    @ReadOnlyComposable
    fun resources(): Resources {
        LocalConfiguration.current
        return LocalContext.current.resources
    }

    fun NavGraphBuilder.appGraph(appState: SyndicateManagerAppState) {
        navigation(
            startDestination = LOGIN,
            route = AUTH
        ){
            composable(route=LOGIN){

                LoginScreen(
                    openAndPopUp = {route,popup -> appState.navigateAndPopUp(route,popup)},
                    open = {route -> appState.navigate(route)}
                )
                // to navigate : call navController.navigate("dest"){
                // add this lambda to Pop the auth route
                //    popUpTo("auth"){inclusive = true}
                // }
            }
            composable(route=SIGNUP){
                RegisterScreen(openAndPopUp = {route,popup -> appState.navigateAndPopUp(route,popup)})
            }
            composable(route=RESET_PASSWORD){
                Column {
                    Text(text = "RESET PASSWORD SCREEN")

                }
            }
        }
        navigation(
            startDestination = MONTH_VIEW,
            route =MAIN
        ){
            composable(route= MONTH_VIEW){
                Column {
                    Text(text = "user is ${Repo.user.name}")

                }
            }

        }
        }