package com.ensias.syndicatemanager.ui.view.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NomField(value: String, onNewValue: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        singleLine = true,
        onValueChange = {onNewValue(it)},
        label = { Text(text = "Nom") },
        modifier = Modifier.width(260.dp)
    )

}