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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.ensias.syndicatemanager.models.Month
import com.ensias.syndicatemanager.models.Operation
import com.ensias.syndicatemanager.ui.state.MonthCardUIState
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import java.util.Calendar

@Composable
fun MonthCardScreen(
    m: MonthCardUIState
) { // TODO: add paremeters of the card into the screen
    MonthCardContent(m)
}

@Composable
fun HorizontalLine(
    color: Color = Color.Black,
    strokeWidth: Float = 1f,
    modifier: Modifier = Modifier
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
fun MonthCardContent(m: MonthCardUIState){
    Card(
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
        ),
    ) {

        Row {
            Text(
                text = "${m.month}", //TODO: make it based on uiState
                modifier = Modifier.padding(15.dp),
                style = MaterialTheme.typography.displayLarge,
                fontFamily = FontFamily.Monospace,

                )
            Text(
                text = "${m.year}", //TODO: make it based on uiState
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
                        text = "${m.income} DH", //TODO: make it based on uiState
                        modifier = Modifier.padding(bottom = 10.dp, start = 20.dp),
                        style = MaterialTheme.typography.titleSmall,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.weight(0.8f))

                    Text(
                        text = "${m.previousBalance} DH",
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
                        text = "${m.outcome} DH",
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp),
                        style = MaterialTheme.typography.titleSmall,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.weight(0.8f))
                    Text(
                        text = "${m.balance} DH",
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
       MonthCardContent(MonthCardUIState(3,2024,1200,1000,900,1100))
    }
}