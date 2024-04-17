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
@Preview
@Composable
fun DateFieldPreview() {
    val expenseUiState = ExpenseUiState(type = "Example", date = Date(), amount = 0)
    DateField(expenseUiState = expenseUiState)
}
/*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DateField() {
    var date by remember { mutableStateOf(Date()) }
    val dateFormatter = remember { SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()) }
    var text by remember { mutableStateOf(dateFormatter.format(date)) }

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
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
            date = dateFormatter.parse(newText) ?: date //convertir la nouvelle valeur de texte entrée par l'utilisateur newText en un objet de type Date
                                                        //?:   si la conversion échoue et renvoie null, alors la valeur de date reste inchangee , la valeur précédente de date est conservée.
        },

     keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusRequester.requestFocus()
            }
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDate() {
    Column (modifier=Modifier.fillMaxSize()){
        DateField()
    }
}
*/










/*
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DateField(onDateSelected: (Date) -> Unit) {
    var selectedDate by remember { mutableStateOf(Date()) }
    var text by remember { mutableStateOf(SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(selectedDate)) }

    TextField(
        value = text,
        onValueChange = {
             newText ->
            // Supprimer les caractères non valides (sauf les slashs) et les remplacer par "0"
           val sanitizedText = newText.map { if (it.isDigit() || it == '/') it else '0' }.joinToString("")

            // Extraire les parties de la date
            val dayPart = sanitizedText.substring(0, minOf(sanitizedText.length, 2))
            val monthPart = sanitizedText.substring(3, minOf(sanitizedText.length, 5))
            val yearPart = sanitizedText.substring(6, minOf(sanitizedText.length, 10))

            // Recréer la date avec les slashs fixes
            val formattedText = buildString {
                append(dayPart.padEnd(2, '0'))
                append("/")
                append(monthPart.padEnd(2, '0'))
                append("/")
                append(yearPart.padEnd(4, '0'))
            }

            text = formattedText
            selectedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(text) ?: Date()
            onDateSelected(selectedDate)
        },
        label = { Text("Date") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview
@Composable
fun DateFieldPreview() {
    DateField(onDateSelected = {})
}

*/













/*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*
@Composable
fun MyApp() {
    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }

    BackHandler {
        // Handle back button press if needed
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Docked Date Picker") }) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            DockedDatePicker(selectedDate = selectedDate) {
                selectedDate = it
            }
        }
    }
}

@Composable
fun DockedDatePicker(
    selectedDate: Calendar,
    onDateSelected: (Calendar) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(64.dp),
        color = MaterialTheme.colors.primary
    ) {
        DatePicker(
            selectedDate = selectedDate,
            onDateSelected = onDateSelected
        )
    }
}

@Composable
fun DatePicker(
    selectedDate: Calendar,
    onDateSelected: (Calendar) -> Unit
) {
    val datePicker = remember { MaterialDatePicker.Builder.datePicker().build() }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { datePicker.showNow(supportFragmentManager, "date_picker") }
        ) {
            Text("Select Date")
        }
    }

    DisposableEffect(datePicker) {
        val callback = MaterialDatePicker.OnDateSetListener { _, year, month, dayOfMonth ->
            selectedDate.set(year, month, dayOfMonth)
            onDateSelected(selectedDate)
        }

        datePicker.addOnPositiveButtonClickListener(callback)

        onDispose {
            datePicker.removeOnPositiveButtonClickListener(callback)
        }
    }
}

@Preview
@Composable
fun PreviewDockedDatePicker() {
    val selectedDate = remember { Calendar.getInstance() }
    DockedDatePicker(selectedDate = selectedDate) { }
}*/
/*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DateField(
    initialValue: TextFieldValue = TextFieldValue(Calendar.getInstance().time.toFormattedString())
) {
    var showDialog by remember { mutableStateOf(false) }
    var textState by remember { mutableStateOf(initialValue) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        BasicTextField(
            value = textState,
            onValueChange = { textState = it },
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = { showDialog = true }) {
            Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Calendrier")
        }
    }

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            onDateSelected = { selectedDate ->
                textState = TextFieldValue(selectedDate.toFormattedString())
                showDialog = false
            }
        )
    }
}

fun Date.toFormattedString(): String {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return format.format(this)
}

@Composable
fun DatePickerDialog(
    onDismissRequest: () -> Unit,
    onDateSelected: (Date) -> Unit
) {
    val selectedDate = remember { mutableStateOf(Calendar.getInstance()) }
    Dialog(onDismissRequest = onDismissRequest) {
        Surface {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Display the DatePicker
                // You can implement your own DatePicker here or use a library that supports Material3
                Text("DatePicker goes here")

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

@Preview
@Composable
fun PreviewDateField() {
    DateField()
}*/

/*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.ensias.syndicatemanager.R
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@Composable
fun DateTextField() {
    SyndicateManagerTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)

        ) {
            val dateFormatter = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }
            var date by remember { mutableStateOf(Calendar.getInstance().time) }
            var isDatePickerVisible by remember { mutableStateOf(false) }
            val formattedDate = dateFormatter.format(date)

            Column {
                TextField(
                    value = formattedDate,

                   // readOnly = false,
                    singleLine = true,
                    onValueChange = {date = dateFormatter.parse(it) ?: date

                    },
                    label = { Text(text = stringResource(id = R.string.DATE)) },
                    trailingIcon = {
                        CalendarIcon(onClick = { isDatePickerVisible = true })
                    },
                    modifier = Modifier.padding(15.dp)       // colors = MaterialTheme.colorScheme.scrim

                )
                if (isDatePickerVisible) {
                    calendrierbutton { onDateSelected -> date }
                }

            }

        }
    }

}

@Composable
fun CalendarIcon(onClick: () -> Unit) {
    IconButton(onClick =onClick){
        Icon(imageVector = Icons.Filled.DateRange, contentDescription = "")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun calendrierbutton(onDateSelected: (Date) -> Unit) {
    var isDatePickerVisible = remember { mutableStateOf(true) }
    val datePickerState = rememberDatePickerState()
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    var dateSelected by remember { mutableStateOf(Calendar.getInstance().time) }
    if (isDatePickerVisible.value) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled = remember {
            derivedStateOf { datePickerState.selectedDateMillis != null }
        }
        DatePickerDialog(
            onDismissRequest = { isDatePickerVisible.value = false },
            confirmButton = {
                Button(
                    onClick = {

                        onDateSelected(dateSelected)
                        //   dateSelected=
                        isDatePickerVisible.value = false
                        snackScope.launch {
                            snackState.showSnackbar(
                                "Selected date timestamp: ${datePickerState.selectedDateMillis}"
                            )
                        }
                    }, enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            modifier = Modifier.padding(16.dp),
            dismissButton = {
                TextButton(
                    onClick = {
                        isDatePickerVisible.value = false
                    }
                ) {
                    Text("Cancel")
                }
            },
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 16.dp,
            colors = DatePickerDefaults.colors(),
            content = {
                //DatePicker(state = datePickerState)
                DatePicker(
                    state = datePickerState,
                    modifier = Modifier.fillMaxWidth(),
                    headline = null,
                    title = null
                )
            },

            )
    }
}


@PreviewLightDark
@Composable
fun CalendarTextFieldPreview() {
    DateTextField()
}
*/


/*
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ensias.syndicatemanager.R
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import java.time.LocalDate
import java.time.ZoneOffset

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarPicker() {
        val calendarIcon = Icons.Filled.DateRange // Icône du calendrier

        SyndicateManagerTheme {
                Column(
                        modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.background)
                ) {
                        var selectedDate by remember { mutableStateOf(LocalDate.now()) }
                        var expanded by remember { mutableStateOf(false) }

                        Box {
                                // Affiche le TextField avec l'icône du calendrier pour ouvrir le DatePicker
                                TextField(
                                        modifier = Modifier
                                                .padding(15.dp)
                                                .fillMaxWidth()
                                                .align(Alignment.CenterStart), // Alignement à gauche dans la boîte
                                        value = selectedDate.toString(),
                                        onValueChange = { /* Do nothing */ },
                                        readOnly = true,
                                        singleLine = true,
                                        label = { Text(text = stringResource(id = R.string.TYPE_DE_DEPENSE)) },
                                        trailingIcon = {
                                                IconButton(onClick = { expanded = !expanded }, ) {
                                                 //       Icon(imageVector = calendarIcon, contentDescription = "Calendrier") // Utiliser l'icône du calendrier
                                                }
                                        },
                                     //   colors = ExposedDropdownMenuDefaults.textFieldColors(),
                                )

                                // Affiche le DatePicker au-dessus du TextField lorsqu'il est expandu
                                if (expanded) {
                                        DatePicker(
                                                state = rememberDatePickerState(initialSelectedDateMillis = selectedDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()),
                                                modifier = Modifier
                                                        .fillMaxWidth()
                                                        .align(Alignment.TopCenter),// Alignement en haut dans la boîte
                                                       // .offset(y = (-DatePickerDefaults.HeaderHeight).dp), // Décalage vers le haut pour superposer le TextField
                                                title = {
                                                        Text(
                                                                text = "Sélectionnez une date",
                                                                style = TextStyle(fontWeight = FontWeight.Bold)
                                                        )
                                                },
                                                showModeToggle = false, // Désactiver le mode de commutation

                                        )
                                }
                        }
                }
        }
}
@Composable
fun IconButton1(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        imageVector: ImageVector,
        contentDescription: () -> Unit
) {
        androidx.compose.material3.IconButton(onClick = onClick, modifier = modifier) {
                Icon(imageVector = imageVector, contentDescription = " ")
        }
}
*/




/* last chose
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun DateField(
    initialValue: TextFieldValue = TextFieldValue(Calendar.getInstance().time.toFormattedString())
) {
    var showDialog by remember { mutableStateOf(false) }
    var textState by remember { mutableStateOf(initialValue) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        TextField(
            value = textState,
            onValueChange = { textState = it },
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = { showDialog = true }) {
            Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Calendrier")
        }
    }

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            onDateSelected = { selectedDate ->
                textState = TextFieldValue(selectedDate.toFormattedString())
                showDialog = false
            }
        )
    }
}
fun Date.toFormattedString(): String {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return format.format(this)
}

@Composable
fun DatePickerDialog(
    onDismissRequest: () -> Unit,
    onDateSelected: (Date) -> Unit
) {
    val selectedDate = remember { mutableStateOf(Calendar.getInstance()) }
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
@PreviewLightDark
@Composable
fun PreviewDateField() {
    SyndicateManagerTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
    DateField()
        }}
}*/
