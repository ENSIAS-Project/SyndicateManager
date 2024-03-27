package com.ensias.syndicatemanager.ui.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.ensias.syndicatemanager.R
import com.ensias.syndicatemanager.models.Month
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme

@Composable
fun MonthCard(
    m : Month
){ // TODO: add paremeters of the card into the screen
    Card(
        colors = CardDefaults.cardColors(
            //if (isSelected) MaterialTheme.colorScheme.primaryContainer else
             containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .fillMaxWidth(),
         //   .size(width=250.dp,height = 100.dp),
        shape=RoundedCornerShape(corner= CornerSize(20.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp
        ),
    ) {
        Row {
            Text(text = "${m.month}",
                modifier = Modifier.padding(15.dp),
                style = MaterialTheme.typography.displayLarge,
                fontFamily = FontFamily.Cursive,
                
                )
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewMonthCard(){
    SyndicateManagerTheme {
        MonthCard(Month(income = 1600, outcome = 1200, balance = 1000, prevBalalnce = 400, month = 3, year = 2024))
    }
}