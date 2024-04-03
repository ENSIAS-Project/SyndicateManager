package com.ensias.syndicatemanager.ui.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun DetailCardScreen(descrp: String, m: Month) { // TODO: add paremeters of the card into the screen
    DetailCardContent()
}

@Composable
fun DetailCardContent(){
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
                text = "${"descrp"}",
                //  modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.titleMedium,
                fontFamily = FontFamily.Monospace,

                )



            Column(
                modifier = Modifier.padding(start = 50.dp),

                ) {

                Row() {
                    Text(
                        text = "${"m.income"} DH",
                        //  modifier = Modifier.padding( 10.dp),
                        style = MaterialTheme.typography.titleSmall,
                        fontFamily = FontFamily.Monospace,
                        )
                }
                Row {
                    Text(
                        text = "${"m.outcome"} DH",
                        style = MaterialTheme.typography.titleSmall,
                        fontFamily = FontFamily.Monospace,
                        )
                }
            }
        }
    }
}
@PreviewLightDark

@Composable
fun PreviewListMonth() {
    SyndicateManagerTheme {
        DetailCardContent()
    }
}
