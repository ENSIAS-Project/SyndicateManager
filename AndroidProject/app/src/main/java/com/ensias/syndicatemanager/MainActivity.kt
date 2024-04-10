    package com.ensias.syndicatemanager

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import androidx.navigation.navArgument
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import com.ensias.syndicatemanager.ui.view.DetailMonthScreen
import com.ensias.syndicatemanager.ui.view.ListMonthScreen
import com.ensias.syndicatemanager.ui.view.LoginScreen
import com.ensias.syndicatemanager.ui.view.ResetPasswordScreen
import com.ensias.syndicatemanager.ui.view.SignUpScreen
import com.ensias.syndicatemanager.ui.view.SnackbarManager
import com.ensias.syndicatemanager.ui.view.components.LoginBackground
import com.ensias.syndicatemanager.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.navigation.NavType

    @AndroidEntryPoint
    class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           SyndicateManagerTheme {
               val mainViewModel : MainViewModel = hiltViewModel()
               Surface(color = MaterialTheme.colorScheme.background) {
                   val appState = rememberAppState()
                   NavDrawer(appState = appState,
                                drawercontent = {DrawerContent(
                                    items = NavDrawerItems.items,
                                    icons = NavDrawerItems.icons,
                                )} ,
                       ) {
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
                           floatingActionButton = {
                               Fab(
                                   appState = appState,
                                   drawerState = appState.drawerState,
                                   scope = rememberCoroutineScope(),
                                   logout = {
                                       mainViewModel.logout()
                                       appState.navigateAndPopUp(AUTH, MAIN)
                                       appState.logged.value = false
                                   }
                               )
                           }
                       ) {innerPaddingModifier ->
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
        coroutineScope: CoroutineScope = rememberCoroutineScope(),
        drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
        logged: MutableState<Boolean> = rememberSaveable { (mutableStateOf(false)) },
        IsAdmin: MutableState<Boolean> = rememberSaveable { (mutableStateOf(false)) }
    ) =
        remember( navController, snackbarManager, resources, snackbarHostState,coroutineScope,drawerState) {
            SyndicateManagerAppState(navController, snackbarManager, resources, snackbarHostState,drawerState,logged,IsAdmin,coroutineScope)
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
                LoginBackground(450,(-40))
                LoginScreen(
                    openAndPopUp = {route,popup -> appState.navigateAndPopUp(route,popup)},
                    open = {route -> appState.navigate(route)},
                    toggleAdminUservalues = {isadmin, logged -> ToggleAdminUservalues(appState,isadmin,logged) }
                )
                // to navigate : call navController.navigate("dest"){
                // add this lambda to Pop the auth route
                //    popUpTo("auth"){inclusive = true}
                // }
            }
            composable(route=SIGNUP){
             // RegisterScreen(openAndPopUp = {route,popup -> appState.navigateAndPopUp(route,popup)})
                LoginBackground(550,(-80))
                SignUpScreen(
                    openAndPopUp = {route,popup -> appState.navigateAndPopUp(route,popup)},
                    open = {route -> appState.navigate(route)}
                )
           }
            composable(route=RESET_PASSWORD){
                LoginBackground(400,(-50))
                ResetPasswordScreen(
                    openAndPopUp = {route,popup -> appState.navigateAndPopUp(route,popup)},
                    open = {route -> appState.navigate(route)}
                )
            }
        }
        navigation(
            startDestination = MONTH_VIEW,
            route =MAIN
        ){
            composable(route= MONTH_VIEW){
                ListMonthScreen(
                    open= {route -> appState.navigate(route)},
                    contentPadding = PaddingValues(20.dp))
            }
            composable(
                route= MONTH_DETAILS,
                arguments = listOf(
                    navArgument(MONTH_ID) { type = NavType.StringType },
                )

            ){backStackEntry ->
                // get params from the navGraph
                val monthID = backStackEntry.arguments?.getString(MONTH_ID)
                val monthval = backStackEntry.arguments?.getString(MONTH_VAL)!!.toInt()
                val yearval = backStackEntry.arguments?.getString(YEAR_VAL)!!.toInt()
                 DetailMonthScreen(monthval, yearval,monthID)
            }
        }
    }

    @Composable
    fun DrawerContent(items:List<Int>,icons:List<Int>){
        ModalDrawerSheet(
            modifier = Modifier
                .fillMaxHeight()
                .width(250.dp)
        ) {
            Spacer(Modifier.height(100.dp))
            items.forEachIndexed { index, item ->
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = icons[index]),
                            contentDescription = null,
                            modifier = Modifier.size(40.dp)
                        )
                    },
                    label = { Text(text = stringResource(id = item)) },
                    selected = true, //TODO: fix this
                    onClick = { } //TODO: implement this
                )
                Spacer(Modifier.height(25.dp))
            }
        }
    }

    @Composable
    fun NavDrawer(appState: SyndicateManagerAppState,drawercontent:@Composable () -> Unit,content:@Composable () -> Unit){
        ModalNavigationDrawer(
            drawerState = appState.drawerState,
            drawerContent = drawercontent,
        content = content,
            gesturesEnabled = appState.isAdmin.value, // to avoid users from using it
        )
    }

    @Composable
    fun Fab(
        appState:SyndicateManagerAppState,
        drawerState: DrawerState,
        scope:CoroutineScope,
        logout:()->Unit)
    {
        if(appState.logged.value){
           if(appState.isAdmin.value){
               FloatingActionButton(
                   onClick = {
                       scope.launch {
                            if(drawerState.isClosed)drawerState.open() else drawerState.close()
                       }
                   }
               ){ Icon(Icons.Filled.Add,null)}
           } else{
               FloatingActionButton(
                   onClick = {
                       scope.launch {
                           if(appState.logged.value){
                               logout()
                           }
                       }
                   }
               ){ Icon(Icons.Filled.Close,null)}
           }
        }
    }
    fun ToggleAdminUservalues(appState:SyndicateManagerAppState,isadmin:Boolean,logged:Boolean){
        appState.isAdmin.value = isadmin
        appState.logged.value = logged
    }