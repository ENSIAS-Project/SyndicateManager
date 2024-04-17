package com.ensias.syndicatemanager.ui.view.components

import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import java.util.Calendar
import java.util.Date

@Composable
fun DatePickerDialog(
    onDismissRequest: () -> Unit,
    onDateSelected: (Date) -> Unit,
    initialDate: Date
) {val selectedDate = remember { mutableStateOf(Calendar.getInstance()) }
    Dialog(onDismissRequest = onDismissRequest) {
        Surface {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Display the DatePicker
                AndroidView(factory = { context ->
                    val datePicker = DatePicker(context)
                    datePicker.init(
                        selectedDate.value.get(Calendar.YEAR),
                        selectedDate.value.get(Calendar.MONTH),
                        selectedDate.value.get(Calendar.DAY_OF_MONTH)
                    ) { _, year, monthOfYear, dayOfMonth ->
                        selectedDate.value = Calendar.getInstance().apply {
                            set(year, monthOfYear, dayOfMonth)
                        }
                    }
                    datePicker
                })

                // OK and Cancel buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(onClick = {
                        onDateSelected(selectedDate.value.time)
                        onDismissRequest()
                    }) {
                        Text(text = "OK")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { onDismissRequest() }) {
                        Text(text = "Cancel")
                    }
                }
            }
        }
    }
}