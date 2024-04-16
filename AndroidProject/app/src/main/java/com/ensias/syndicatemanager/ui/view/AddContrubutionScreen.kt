package com.ensias.syndicatemanager.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
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
import com.ensias.syndicatemanager.models.SpendType
import com.ensias.syndicatemanager.ui.state.ContribUiState
import com.ensias.syndicatemanager.ui.state.ExpenseUiState
import com.ensias.syndicatemanager.ui.view.components.BgForAllScreens
import com.ensias.syndicatemanager.ui.view.components.DateField
import com.ensias.syndicatemanager.ui.view.components.DropDownMenuExpense
import com.ensias.syndicatemanager.ui.view.components.SuffixTextField

@Composable
fun AddContributionScreen(){

}
/*
@Composable
fun AddContributionContent(
    expenseUiState: ContribUiState,
    userlist: List<SpendType>,
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
        if(!expenseUiState.pendingOperation){
            ElevatedButtonExample(onAddExpenseClicked)
        }else{
            CircularProgressIndicator(
                modifier = Modifier,
                strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth,
                trackColor = MaterialTheme.colorScheme.onSurface,
                strokeCap = ProgressIndicatorDefaults.CircularIndeterminateStrokeCap
            )
        }
    }
}

@PreviewLightDark
@Composable
fun Preview(){
    AddContributionContent()
}
*/