package com.ensias.syndicatemanager.ui.view.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ensias.syndicatemanager.R
import com.ensias.syndicatemanager.ui.state.ExpenseUiState
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenuExpense(expenseUiState: ExpenseUiState) {
    SyndicateManagerTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
               // .background(MaterialTheme.colorScheme.background)

        ) {

            var options by remember {
                mutableStateOf(
                    listOf(
                        "Nottoyage",
                        "Electricite",
                    )
                )
            }

            // val options = listOf("Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread")
            var expanded by remember { mutableStateOf(false) }
            var selectedOption by remember(expenseUiState) { mutableStateOf(expenseUiState.type) }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                ) {
                    val textFieldWidth = when {
                        // Adapter aux différentes tailles d'écran et orientations
                        isScreenSmall() -> 260.dp // Utiliser la valeur pour les petits écrans
                        isScreenMedium() -> 540.dp // Utiliser la valeur pour les écrans de taille moyenne
                        else -> 600.dp // Utiliser la valeur par défaut pour les grands écrans
                    }
                    Column() {
                        TextField(
                            // The `menuAnchor` modifier must be passed to the text field for correctness.
                            modifier = Modifier
                                .menuAnchor()
                                .width(textFieldWidth)
                             .padding(start = 20.dp),
                            value = selectedOption,
                            onValueChange = {},
                            readOnly = true,
                            singleLine = true,
                            label = { Text(text = stringResource(id = R.string.TYPE_DE_DEPENSE)) },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            //   shape= RoundedCornerShape(15.dp)
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                        ) {
                            options.forEach { option ->
                                OptionItem(
                                    option = option,
                                    onDeleteClicked = {
                                        options = options.filterNot { it == option }
                                    },
                                    onOptionSelected = { selectedOption =it
                                        //text = selectedOption
                                        //expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = AbsoluteAlignment.Left
                ) {
                    AddOptionButton { newOption ->
                        options = options + newOption
                    }
                }

            }
        }
    }

}

@Composable
fun AddOptionButton(onOptionAdded: (String) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    var newOptionText by remember { mutableStateOf("") }


    TextButton(
        onClick = { showDialog = true },

    ) {
        Icon(
            imageVector = Icons.Filled.Build,
            contentDescription = null,
            modifier = Modifier
                .size(25.dp)         )
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = stringResource(id = R.string.AJOUTER_UN_NOUVEAU_TYPE)) },
            text = {
                Column {
                    OutlinedTextField(
                        value = newOptionText,
                        onValueChange = { newOptionText = it },
                        label = { Text(text = stringResource(id = R.string.TYPE_DE_DEPENSE)) },
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onOptionAdded(newOptionText)
                        newOptionText = ""
                        showDialog = false
                    }
                ) {
                    Text(text = stringResource(id = R.string.AJOUTER))
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false }
                ) {
                    Text(text = stringResource(id = R.string.ANNULER))
                }
            }
        )
    }
}

@Composable
fun OptionItem(
    option: String,
    onDeleteClicked: () -> Unit,
    onOptionSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onOptionSelected(option) }
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = option,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = onDeleteClicked,
            modifier = Modifier.size(25.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete",

              //  tint = MaterialTheme.colorScheme.error
            )
        }
    }
}


@Composable
fun isScreenSmall(): Boolean {
    val screenWidthDp: Dp =with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp }
    return screenWidthDp < 600.dp // Vous pouvez ajuster cette valeur selon vos besoins
}

@Composable
fun isScreenMedium(): Boolean {
    val screenWidthDp: Dp =
        with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp }
    return screenWidthDp in 600.dp..900.dp // Vous pouvez ajuster ces valeurs selon vos besoins
}
@Preview
@Composable
fun PreviewDropDownMenuExpense() {
    val expenseUiState = ExpenseUiState(type = "Nettoyage", date = Date(), amount = 0)
    DropDownMenuExpense(expenseUiState = expenseUiState)
}