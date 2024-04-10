package com.ensias.syndicatemanager.ui.view.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.ensias.syndicatemanager.models.Month
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import java.util.Calendar
import java.util.Date

@Composable
fun HorizontalLine(
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    strokeWidth: Float = 1f
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
    ) {
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = strokeWidth,
        )
    }
}

@Composable
fun MonthCard(
    m: Month,
    clickedMonth:(id:String) -> Unit
    ){
    Card(
        onClick = {clickedMonth(m.id)},
        colors = CardDefaults.cardColors(
            //containerColor = if (isSelected) MaterialTheme.colorScheme.surfaceContainer NOTE: no need for selection
            //else MaterialTheme.colorScheme.surfaceContainerLowest,
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(20.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp, focusedElevation = 8.dp
        )
    ) {

        Row {
            Text(
                text = "${getMonth(m.monthDate)+1}",
                modifier = Modifier.padding(15.dp),
                style = MaterialTheme.typography.displayLarge,
                fontFamily = FontFamily.Monospace,

                )
            Text(
                text = "${getYear(m.monthDate)}",
                modifier = Modifier.padding(top = 50.dp),
                style = MaterialTheme.typography.titleSmall,
                fontFamily = FontFamily.Monospace,

                )

            Column(
                modifier = Modifier
                    .padding(start = 10.dp, top = 15.dp)
            )

            {
                Row {
                    Text(
                        text = "${m.debit} DH",
                        modifier = Modifier.padding(bottom = 10.dp, start = 20.dp),
                        style = MaterialTheme.typography.titleSmall,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.weight(0.8f))

                    Text(
                        text = "${m.prevBalance} DH",
                        modifier = Modifier.padding(bottom = 10.dp, start = 50.dp),
                        style = MaterialTheme.typography.titleSmall,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.weight(0.2f))
                }
                HorizontalLine(color = MaterialTheme.colorScheme.primary, strokeWidth = 4f)
                Row {
                    Text(
                        text = "${m.credit} DH",
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp),
                        style = MaterialTheme.typography.titleSmall,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.weight(0.8f))
                    Text(
                        text = "${m.currBalance} DH",
                        modifier = Modifier.padding(top = 10.dp, start = 60.dp),
                        style = MaterialTheme.typography.titleSmall,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.weight(0.2f))

                }
            }

        }

    }
}

@PreviewLightDark
@Composable
fun PreviewMonthCard() {
    SyndicateManagerTheme {
       MonthCard(Month(), clickedMonth = {})
    }
}

fun getYear(date:Date):Int{
    var cal = Calendar.getInstance()
    cal.time = date
    return cal.get(Calendar.YEAR)
}
fun getMonth(date:Date):Int{
    var cal = Calendar.getInstance()
    cal.time = date
    return cal.get(Calendar.MONTH)
}
