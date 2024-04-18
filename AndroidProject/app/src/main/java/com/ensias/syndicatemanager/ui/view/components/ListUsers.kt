package com.ensias.syndicatemanager.ui.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ensias.syndicatemanager.R
import com.ensias.syndicatemanager.models.User
import com.ensias.syndicatemanager.ui.state.ContributionUiState
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListUsers(
    contributionUiState: ContributionUiState,
    users:List<User>
    //  options : List<User>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        var expanded by remember { mutableStateOf(false) }
        var selectedOption by remember { mutableStateOf("") }
        Row {
            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }) {
                TextField(
                    modifier = Modifier
                        .menuAnchor()
                        .padding(start = 20.dp),
                    value = selectedOption,
                    onValueChange = {},
                    readOnly = true,
                    singleLine = true,
                    label = { Text(text = stringResource(id = R.string.AFFECTER_UN_UTILISATEUR)) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    //   shape= RoundedCornerShape(15.dp)
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {

                    users.forEach { option ->
                        OptionItem(
                            option = option.name
                        ) {
                            selectedOption = option.name
                            contributionUiState.user = option
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewListUsers() {
    val contrUiState = ContributionUiState(User(), Calendar.getInstance().time,0)
    ListUsers(ContributionUiState(), listOf())
}