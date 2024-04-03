package com.ensias.syndicatemanager.ui.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme


@Composable
fun sheet (){
    BgForAllScreens()
    Column(modifier=Modifier.padding(end = 30.dp, bottom = 40.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End

    ){
         button()
     }

    }


//float action button
@Composable
fun button(){
   /* LargeFloatingActionButton(
        onClick = {  },
        shape = CircleShape,
    ) {
        Icon(Icons.Filled.Add, "Large floating action button")
    }*/

       FloatingActionButton(
           onClick = { },
       ) {
           Icon(Icons.Filled.Add, "Floating action button.")

   }
}
@PreviewLightDark
@Composable
fun previewFAB(){
    SyndicateManagerTheme {
        sheet()
    }
}