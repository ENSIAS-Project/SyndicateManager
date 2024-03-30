package com.ensias.syndicatemanager.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.ensias.syndicatemanager.models.Month
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import com.ensias.syndicatemanager.ui.view.components.BgForAllScreens
import com.ensias.syndicatemanager.ui.view.components.MonthCard


@Composable
fun ListMonthScreen(contentPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(contentPadding)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BgForAllScreens()

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),

            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(10) {
                MonthCard(
                    Month(
                        income = 1600,
                        outcome = 1200,
                        balance = 1000,
                        prevBalalnce = 400,
                        month = 3,
                        year = 2024
                    )
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
            ListMonthScreen(PaddingValues(10.dp))
        }
    }
}