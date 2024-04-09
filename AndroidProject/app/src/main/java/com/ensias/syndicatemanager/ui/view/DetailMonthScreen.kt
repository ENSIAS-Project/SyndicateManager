package com.ensias.syndicatemanager.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.ensias.syndicatemanager.models.Month
import com.ensias.syndicatemanager.models.Operation
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import com.ensias.syndicatemanager.ui.view.components.BgForAllScreens
import com.ensias.syndicatemanager.ui.view.components.DetailCardScreen
import java.util.Calendar


@Composable
fun DetailMonthScreen(descp:String,m:Month) {
    DetailMonthContent(descp,m)
}

@Composable
fun DetailMonthContent(descp:String,m:Month){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box{
            Row{
                Text(
                    text = "${m.monthDate}", //TODO: find a better alternative
                    modifier = Modifier.padding(15.dp),
                    style = MaterialTheme.typography.displayLarge,
                    fontFamily = FontFamily.Monospace,

                    )
                Text(
                    text = "/${m.monthDate}", //TODO: find a better alternative
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
                DetailCardScreen("a test description",Month(
                    prevBalalnce = 160,
                    operations = ArrayList<Operation>(),
                    monthDate = Calendar.getInstance().time
                    )
                )
            }

        }
    }
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
            DetailMonthScreen("cotisation mahmoud", //FIXME : description isn't for this purpose
                Month(
                prevBalalnce = 160,
                operations = ArrayList<Operation>(),
                monthDate = Calendar.getInstance().time
                )
            )
        }
    }
}