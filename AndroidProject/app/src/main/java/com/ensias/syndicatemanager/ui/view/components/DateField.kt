package com.ensias.syndicatemanager.ui.view.components



import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.ensias.syndicatemanager.R
import com.ensias.syndicatemanager.ui.state.ContributionUiState
import com.ensias.syndicatemanager.ui.state.ExpenseUiState
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateField(expenseUiState: ExpenseUiState) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        var date by remember { mutableStateOf(expenseUiState.date) }
        val dateFormatter = remember { SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()) }
        var text by remember { mutableStateOf(dateFormatter.format(date)) }
        var isDatePickerOpen by remember { mutableStateOf(false) }
        val context = LocalContext.current
        val focusRequester = remember { FocusRequester() }
        val keyboardController = LocalSoftwareKeyboardController.current

        Row {
            TextField(
                label = { Text(text = stringResource(id = R.string.DATE)) },

                modifier = Modifier
                    .focusRequester(focusRequester)
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            keyboardController?.show()
                        }
                    },
                value = text,
                onValueChange = { newText ->
                    text = newText
                        date = dateFormatter.parse(newText) ?: date
                     expenseUiState.date=date //update
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusRequester.requestFocus()
                    }
                ), trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Calendar Icon",
                        tint = Color.Gray,
                        modifier = Modifier.clickable {
                            isDatePickerOpen = true
                        }
                    )
                }
            )
            if (isDatePickerOpen) {
                DatePickerDialog(
                    onDismissRequest = { isDatePickerOpen = false },
                    onDateSelected = { newDate ->
                        date = newDate
                        text = dateFormatter.format(date)
                        isDatePickerOpen = false
                        expenseUiState.date=date
                    },
                    initialDate = date
                )
            }
        }
    }
}
@Composable
fun DateField(expenseUiState: ContributionUiState) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        var date by remember { mutableStateOf(expenseUiState.date) }
        val dateFormatter = remember { SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()) }
        var text by remember { mutableStateOf(dateFormatter.format(date)) }
        var isDatePickerOpen by remember { mutableStateOf(false) }
        val context = LocalContext.current
        val focusRequester = remember { FocusRequester() }
        val keyboardController = LocalSoftwareKeyboardController.current

        Row {
            TextField(
                label = { Text(text = stringResource(id = R.string.DATE)) },

                modifier = Modifier
                    .focusRequester(focusRequester)
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            keyboardController?.show()
                        }
                    },
                value = text,
                onValueChange = { newText ->
                    text = newText
                    date = dateFormatter.parse(newText) ?: date
                    expenseUiState.date=date //update
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusRequester.requestFocus()
                    }
                ), trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Calendar Icon",
                        tint = Color.Gray,
                        modifier = Modifier.clickable {
                            isDatePickerOpen = true
                        }
                    )
                }
            )
            if (isDatePickerOpen) {
                DatePickerDialog(
                    onDismissRequest = { isDatePickerOpen = false },
                    onDateSelected = { newDate ->
                        date = newDate
                        text = dateFormatter.format(date)
                        isDatePickerOpen = false
                        expenseUiState.date=date
                    },
                    initialDate = date
                )
            }
        }
    }
}
@Preview
@Composable
fun DateFieldPreview() {
    val expenseUiState = ExpenseUiState(type = "Example", date = Date(), amount = 0)
    DateField(expenseUiState = expenseUiState)
}
