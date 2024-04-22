package com.ensias.syndicatemanager.ui.view.components

import android.view.View
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    onDismissRequest: () -> Unit,
    onDateSelected: (Date) -> Unit,
    initialDate: Date
) {
    val selectedDate = remember { mutableStateOf(Calendar.getInstance()) }
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)

    SyndicateManagerTheme {


    Dialog(onDismissRequest = onDismissRequest) {
        Surface {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                DatePicker(state = datePickerState)
                // Display the DatePicker
               /* AndroidView(factory = { context ->
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
                    val colorResId = if (false) {
                        android.R.color.black // Replace with your dark mode color resource
                    } else {
                        android.R.color.white // Replace with your light mode color resource
                    }
                    ViewCompat.setBackgroundTintList(
                        datePicker,
                        ContextCompat.getColorStateList(context, colorResId)
                    )
                    datePicker
                })*/

                // OK and Cancel buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(onClick = {
                        val time = datePickerState.selectedDateMillis?:0
                        val c = Calendar.getInstance()
                        c.timeInMillis = time
                        onDateSelected(c.time)
                       // onDateSelected(selectedDate.value.time)
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
    }

@PreviewLightDark
@Composable
fun PreviewAddContributionScreen() {
    DatePickerDialog(
        onDismissRequest = {},
        onDateSelected = {},
        initialDate = Date()
    )
}