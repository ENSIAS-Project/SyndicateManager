package com.ensias.syndicatemanager.ui.view.components

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PasswordField(value: String, onNewValue: (String) -> Unit,label:String) {
    OutlinedTextField(
        value = value,
        singleLine = true,
        //TO DO : make it looks like ****** //FIXME: remove comments when the job is done
        visualTransformation = PasswordVisualTransformation(),

        onValueChange = {onNewValue(it)},
        label = { Text(text = label)},
        modifier = Modifier.width(260.dp)
    )

}