package com.ensias.syndicatemanager.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.ensias.syndicatemanager.R
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import kotlinx.coroutines.launch
@PreviewLightDark

@Composable
fun OptionsAdminScreen() {
    SyndicateManagerTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)

        ) {

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

            val scope = rememberCoroutineScope()
            // icons to mimic drawer destinations
            var selectedItem by remember { mutableIntStateOf(0) }

            val items = listOf(
                R.string.GESTION_DES_UTILISATEURS,
                R.string.AJOUT_DE_DEPENSES,
                R.string.AJOUT_DE_COTISATION
            )
            val icons =
                listOf(R.drawable.utilisateur_1, R.drawable.depenser_de_largent, R.drawable.don)
            ModalNavigationDrawer(
                /* Drawer content */
                drawerState = drawerState,
                drawerContent = {
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
                                selected = selectedItem == index,
                                onClick = { selectedItem = index }
                            )
                            Spacer(Modifier.height(25.dp))
                        }


                    }
                }//,gesturesEnabled=false
            ) {
                Scaffold(
                    floatingActionButton = {
                        ExtendedFloatingActionButton(

                            icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                            text = {},
                            onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }
                        )
                    }
                ) {
                   contentPadding ->ListMonthScreen()
                    //  content


                }

            }
        }
    }
}



