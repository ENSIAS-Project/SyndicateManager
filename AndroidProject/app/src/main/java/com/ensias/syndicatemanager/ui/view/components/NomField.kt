package com.ensias.syndicatemanager.ui.view.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ensias.syndicatemanager.R

@Composable
fun NomField(value: String,onNewValue: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        singleLine = true,
        onValueChange = {onNewValue(it)},
        label = { Text(text = stringResource(id = R.string.NAME)) },
        modifier = Modifier.width(260.dp)
    )

}