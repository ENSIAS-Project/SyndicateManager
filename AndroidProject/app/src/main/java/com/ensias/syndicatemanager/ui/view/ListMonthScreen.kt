package com.ensias.syndicatemanager.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ensias.syndicatemanager.models.Month
import com.ensias.syndicatemanager.models.Operation
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import com.ensias.syndicatemanager.ui.view.components.BgForAllScreens
import com.ensias.syndicatemanager.ui.view.components.MonthCardContent
import com.ensias.syndicatemanager.ui.view.components.button
import com.ensias.syndicatemanager.viewmodels.AuthViewModel
import com.ensias.syndicatemanager.viewmodels.MonthViewModel
import java.util.Calendar


@Composable
fun ListMonthScreen(
    contentPadding: PaddingValues,
    monthViewModel: MonthViewModel = hiltViewModel()
) {
   ListMonthContent(contentPadding,{monthViewModel.tempimpldata()})
}

@Composable
fun ListMonthContent(
    contentPadding: PaddingValues,
    testData:() -> Unit
    ){
    BgForAllScreens()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(contentPadding)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = testData) {
            Text(text = "test data")
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),

            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(10) {
                MonthCardContent(
                )
            }
        }
    }
}

@PreviewLightDark

@Composable
fun PreviewListMonth() {
    SyndicateManagerTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ListMonthContent(PaddingValues(10.dp),{})
        }
    }
}