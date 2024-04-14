package com.ensias.syndicatemanager.ui.view.components

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.ensias.syndicatemanager.models.Operation
import com.ensias.syndicatemanager.models.User
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme

@Composable
fun ContribCard(op: Operation){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
            //else MaterialTheme.colorScheme.surfaceBright,
        ),
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(20.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
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
                text = op.user.name,//op.id,
                //  modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.titleLarge,
                fontFamily = FontFamily.Monospace,
                color = MaterialTheme.colorScheme.onSurfaceVariant

            )
            Spacer(modifier = Modifier.weight(1F))
            Column(
                modifier = Modifier.padding(start = 50.dp),

                ) {

                Row() {
                    Text(
                        text = "${op.value} DH",
                        //  modifier = Modifier.padding( 10.dp),
                        style = MaterialTheme.typography.titleSmall,
                        fontFamily = FontFamily.SansSerif,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

            }
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewContribMonth() {
    SyndicateManagerTheme {
        ContribCard(
            Operation(type = "c",user = User(name = "nisrine",id="another easter egg"))
        )
    }
}