package com.ensias.syndicatemanager.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensias.syndicatemanager.R
import com.ensias.syndicatemanager.ui.state.ExpenseUiState
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import com.ensias.syndicatemanager.ui.view.components.BgForAllScreens
import com.ensias.syndicatemanager.ui.view.components.DateField
import com.ensias.syndicatemanager.ui.view.components.DropDownMenuExpense
import com.ensias.syndicatemanager.ui.view.components.SuffixTextField
import java.util.Date

@Composable
fun AddExpenseScreen(expenseUiState:ExpenseUiState,onAddExpenseClicked:(ExpenseUiState)->Unit) {
AddExpenseContent(expenseUiState=expenseUiState,onAddExpenseClicked=onAddExpenseClicked)
}

@Composable
fun AddExpenseContent(
    expenseUiState:ExpenseUiState,onAddExpenseClicked: (ExpenseUiState) -> Unit)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BgForAllScreens()
        Spacer(modifier = Modifier.padding(20.dp))

        Text(
            text = stringResource(id = R.string.AJOUT_DE_DEPENSES),
            style = TextStyle(
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.padding(25.dp))

        DropDownMenuExpense(expenseUiState)
        Spacer(modifier = Modifier.padding(20.dp))

        DateField(expenseUiState)
        Spacer(modifier = Modifier.padding(20.dp))

        SuffixTextField(
            value = expenseUiState.amount.toString(),
            onValueChange = {newText -> expenseUiState.amount = newText.toIntOrNull() ?: 0},
            suffixText = "DH",
        )
        Spacer(modifier = Modifier.padding(50.dp))

        ElevatedButtonExample() {

        }

    }
}


@Composable
fun ElevatedButtonExample(onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier.padding(16.dp),
    )
    {
        Text(text = stringResource(id = R.string.AJOUTER))
    }
}

@PreviewLightDark
@Composable
fun PreviewAddExpenseScreen() {
    SyndicateManagerTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val expenseUiState = ExpenseUiState(
                type = "Electricite",  // Valeur de type de dépense
                date = Date(),         // Date actuelle
                amount = 0          // Montant de la dépense
            )
          AddExpenseContent(expenseUiState, onAddExpenseClicked = {})
        }

    }
}
