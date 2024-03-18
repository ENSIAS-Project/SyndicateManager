package com.ensias.syndicatemanager.ui.view.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PasswordField(value: String, onNewValue: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        singleLine = true,
        //TODO : make it looks like ******
        onValueChange = {onNewValue(it)},
        label = { Text(text = "Password")}
    )

}