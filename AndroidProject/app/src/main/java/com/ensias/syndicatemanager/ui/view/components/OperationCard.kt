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
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import java.util.Calendar
import java.util.Date

@Composable
fun OperationCard(op:Operation){
        if(op.type.equals("s")){
            SpendCard(op)
        }else{
            ContribCard(op)
        }

}
fun date(date: Date):String{
    val c = Calendar.getInstance()
    c.time = date
    return "${c.get(Calendar.DAY_OF_MONTH)}/${c.get(Calendar.MONTH)+1}/${c.get(Calendar.YEAR)}"
}
@PreviewLightDark

@Composable
fun PreviewListMonth() {
    SyndicateManagerTheme {
        val c = Calendar.getInstance()
        c.set(Calendar.DAY_OF_MONTH,3)
        c.set(Calendar.MONTH,1)
        c.set(Calendar.YEAR,2024)
        OperationCard(

            Operation(date= c.time)
        )
    }
}
