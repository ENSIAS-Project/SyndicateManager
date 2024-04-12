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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ensias.syndicatemanager.R
import com.ensias.syndicatemanager.models.SpendType
import com.ensias.syndicatemanager.ui.state.ExpenseUiState
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import com.ensias.syndicatemanager.ui.view.components.BgForAllScreens
import com.ensias.syndicatemanager.ui.view.components.DateField
import com.ensias.syndicatemanager.ui.view.components.DropDownMenuExpense
import com.ensias.syndicatemanager.ui.view.components.SuffixTextField
import com.ensias.syndicatemanager.viewmodels.OperationViewModel
import java.util.Date

@Composable
fun AddExpenseScreen(
    opvViewModel: OperationViewModel = hiltViewModel()
) {
    val uiState by opvViewModel.expenseUiState
    val optionlist = opvViewModel.expensesTypes
        .collectAsStateWithLifecycle(emptyList())
AddExpenseContent(
    expenseUiState=uiState,
    optionlist = optionlist.value,
    onAddExpenseTypeClicked={id->opvViewModel.addExpenseType(id)}, //TODO:fixthis
    onModifyExpenseTypeClicked ={s,e-> opvViewModel.modifyExpenseType(s,e)},
    onAddExpenseClicked={opvViewModel.addExpense()},
    onValueChanged = {newVal -> opvViewModel.setNewVal(newVal)}
)
}
@Composable
fun AddExpenseContent(
    expenseUiState:ExpenseUiState,
    optionlist: List<SpendType>,
    onAddExpenseTypeClicked: (id:String) -> Unit,
    onModifyExpenseTypeClicked:(id:String,name:String)->Unit,
    onAddExpenseClicked:()->Unit,
    onValueChanged:(newval:String) ->Unit
)
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

        DropDownMenuExpense(expenseUiState,optionlist,onAddExpenseTypeClicked,onModifyExpenseTypeClicked)
        Spacer(modifier = Modifier.padding(20.dp))

        DateField(expenseUiState)
        Spacer(modifier = Modifier.padding(20.dp))

        SuffixTextField(
            value = expenseUiState.amount.toString(),
            onValueChange = onValueChanged,
            suffixText = "DH",
        )
        Spacer(modifier = Modifier.padding(50.dp))

        ElevatedButtonExample(onAddExpenseClicked)

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
                amount = 200          // Montant de la dépense
            )
          AddExpenseContent(expenseUiState, listOf(SpendType(name="test")),{},{ _, _->},{},{})
        }
    }
}
