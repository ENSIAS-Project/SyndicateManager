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
import com.ensias.syndicatemanager.ui.view.roundedCornerShape

@Composable
fun PasswordField(value: String, onNewValue: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        singleLine = true,
        //TODO : make it looks like ******
        visualTransformation = PasswordVisualTransformation(),

        onValueChange = {onNewValue(it)},
        label = { Text(text = "Password")},
        modifier = Modifier.width(260.dp)
         //   .clip(shape= RoundedCornerShape(8.dp))
    )

}