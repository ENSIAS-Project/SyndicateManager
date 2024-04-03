package com.ensias.syndicatemanager.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.ensias.syndicatemanager.models.Month
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import com.ensias.syndicatemanager.ui.view.components.BgForAllScreens

@Composable
fun CardM(descrp: String, m: Month) { // TODO: add paremeters of the card into the screen
    SyndicateManagerTheme {

        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                //else MaterialTheme.colorScheme.surfaceBright,
            ),
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(20.dp)),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
        ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),

                ) {

    Text(
        text = "${descrp}",
        //  modifier = Modifier.padding(10.dp),
        style = MaterialTheme.typography.titleMedium,
        fontFamily = FontFamily.Monospace,

        )



                Column(
                    modifier = Modifier.padding(start = 50.dp),

                    ) {

                    Row() {
                        Text(
                            text = "${m.income} DH",
                            //  modifier = Modifier.padding( 10.dp),
                            style = MaterialTheme.typography.titleSmall,
                            fontFamily = FontFamily.Monospace,

                            )
                    }
                    Row {
                        Text(
                            text = "${m.outcome} DH",
                            style = MaterialTheme.typography.titleSmall,
                            fontFamily = FontFamily.Monospace,

                            )
                    }
                }
            }

        }
    }
}

@Composable
fun DetailMonthScreen(descp:String,m:Month) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box{
            Row{
            Text(
                text = "${m.month}",
                modifier = Modifier.padding(15.dp),
                style = MaterialTheme.typography.displayLarge,
                fontFamily = FontFamily.Monospace,

                )
            Text(
                text = "/${m.year}",
                modifier = Modifier.padding(top = 50.dp),
                style = MaterialTheme.typography.titleSmall,
                fontFamily = FontFamily.Monospace,

                )}
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),

            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(5) {
                CardM("${descp}",Month(
                    income = 1600,
                    outcome = 1200,
                    balance = 1000,
                    prevBalalnce = 400,
                    month = 3,
                    year = 2024))

            }

        }
    }
}

@Composable
fun Boxmonth(){

}


@PreviewLightDark

@Composable
fun PreviewDetail() {
    SyndicateManagerTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BgForAllScreens()
            DetailMonthScreen("cotisation mahmoud", Month(
                income = 1600,
                outcome = 1200,
                balance = 1000,
                prevBalalnce = 400,
                month = 3,
                year = 2024))
        }
    }
}