package com.ensias.syndicatemanager.ui.view.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
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
    month: Month,
    clickedMonth:(id:String,m:Int,y:Int) -> Unit,
    ){
    Card(
        onClick = {clickedMonth(month.id, getMonth(month.monthDate), getYear(month.monthDate)) },
        colors = CardDefaults.cardColors(
            //containerColor = if (isSelected) MaterialTheme.colorScheme.surfaceContainer NOTE: no need for selection
            //else MaterialTheme.colorScheme.surfaceContainerLowest,
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(20.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp, focusedElevation = 1.dp
        )
    ) {

        Row {
            Text(
                text = "${getMonth(month.monthDate)+1}",
                modifier = Modifier.padding(15.dp),
                style = MaterialTheme.typography.displayLarge,
                fontFamily = FontFamily.Monospace,

                )
            Text(
                text = "${getYear(month.monthDate)}",
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
                        text = "${month.debit} DH",
                        modifier = Modifier.padding(bottom = 10.dp, start = 20.dp),
                        style = MaterialTheme.typography.titleSmall,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.weight(0.8f))

                    Text(
                        text = "${month.prevBalance} DH",
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
                        text = "${month.credit} DH",
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp),
                        style = MaterialTheme.typography.titleSmall,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.weight(0.8f))
                    Text(
                        text = "${month.currBalance} DH",
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
       MonthCard(Month(),
           clickedMonth = { s: String, l: Int, l1: Int -> }
       )
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
