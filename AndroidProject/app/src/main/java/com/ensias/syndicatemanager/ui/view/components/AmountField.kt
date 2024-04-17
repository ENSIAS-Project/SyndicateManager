package com.ensias.syndicatemanager.ui.view.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensias.syndicatemanager.R


@Composable
fun SuffixTextField(
    value: String,
    onValueChange: (String) -> Unit,
    suffixText: String,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        TextField(
            label = { Text(text = stringResource(id = R.string.MONTANT)) },

            value = value,
            onValueChange = {
                onValueChange(it)
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            trailingIcon = {
                Text(
                    text = suffixText,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        )
    }
}

@Preview
@Composable
fun MyScreen() {
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        SuffixTextField(
            value = text,
            onValueChange = { newText -> text = newText },
            suffixText = "DH"
        )

    }
}
