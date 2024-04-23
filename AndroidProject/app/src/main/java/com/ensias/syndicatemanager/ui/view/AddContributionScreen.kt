package com.ensias.syndicatemanager.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.ensias.syndicatemanager.models.User
import com.ensias.syndicatemanager.ui.state.ContributionUiState
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import com.ensias.syndicatemanager.ui.view.components.BgForAllScreens
import com.ensias.syndicatemanager.ui.view.components.DateField
import com.ensias.syndicatemanager.ui.view.components.ListUsers
import com.ensias.syndicatemanager.ui.view.components.SuffixTextField
import com.ensias.syndicatemanager.viewmodels.OperationViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun AddContributionScreen(opvViewModel: OperationViewModel = hiltViewModel()) {
    val userlist = opvViewModel.users
        .collectAsStateWithLifecycle(emptyList())
    AddContributionContent(
        contribUiState = opvViewModel.contribiutionUiState.value,
        users =  userlist.value,
        onAddContributionClicked = {opvViewModel.addContribution()}
    ) { newval -> opvViewModel.onContribValueChange(newval) }
}

@Composable
fun AddContributionContent(
    contribUiState: ContributionUiState,
    users: List<User>,
    onAddContributionClicked:()->Unit,
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
            text = stringResource(id = R.string.AJOUT_DE_COTISATION),
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.padding(25.dp))

        ListUsers(contribUiState,users)
        Spacer(modifier = Modifier.padding(20.dp))

      DateFieldContrib(contribUiState)
        Spacer(modifier = Modifier.padding(20.dp))

        SuffixTextField(
            value = contribUiState.amount.toString(), onValueChange = onValueChanged,
            suffixText = "DH",
        )
        Spacer(modifier = Modifier.padding(50.dp))
        if(!contribUiState.pendingOperation){
            ElevatedButtonExample(onAddContributionClicked)
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


@Composable
fun ElevatedButton(onClick: () -> Unit) {
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
@Composable
fun DateFieldContrib(contribUiState: ContributionUiState){
    val date= remember{ mutableStateOf(contribUiState.date) }
    val text= remember { mutableStateOf(SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(contribUiState.date)) }
    DateField(contribUiState )
}
@PreviewLightDark
@Composable
fun PreviewAddContributionScreen() {
    SyndicateManagerTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val contrUiState = ContributionUiState(
                user = User(name = "nisrine"),
                date = Date(),
                amount = 0
            )
            AddContributionContent(contrUiState, listOf(),{}) {}
        }

    }
}