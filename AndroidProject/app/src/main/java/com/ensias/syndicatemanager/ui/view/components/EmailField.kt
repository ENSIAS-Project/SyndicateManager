package com.ensias.syndicatemanager.ui.view.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import java.util.regex.Pattern


@Composable
fun EmailField(value: String,valid:Boolean, onValidation: (Boolean) -> Unit, onNewValue: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        singleLine = true,
        isError = !valid,
        onValueChange = {onNewValue(it)},
        label = { Text(text = "Email")},
        modifier = Modifier.width(260.dp).onFocusChanged { focusState ->
            if(!focusState.hasFocus){
                val email = Pattern.compile("^[\\w\\-\\.]+@([\\w-]+\\.)+[\\w-]{2,}\$") //Email REGEX
                var m = email.matcher(value)
                onValidation(m.matches()|| value.equals("")) // if the field is empty don't show it as a problem
            }
         },
    )
}