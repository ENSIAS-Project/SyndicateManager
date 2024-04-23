package com.ensias.syndicatemanager.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ensias.syndicatemanager.models.Operation
import com.ensias.syndicatemanager.models.SpendType
import com.ensias.syndicatemanager.models.User
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import com.ensias.syndicatemanager.ui.view.components.BgForAllScreens
import com.ensias.syndicatemanager.ui.view.components.OperationCard
import com.ensias.syndicatemanager.ui.view.components.SwipeBAckground
import com.ensias.syndicatemanager.viewmodels.MonthViewModel


@Composable
fun DetailMonthScreen(
    month : Int,
    year : Int,
    id : String?,
    monthViewModel: MonthViewModel = hiltViewModel()
) {
    val opList = monthViewModel.getOperatioFlow(id)
        .collectAsStateWithLifecycle(emptyList())
    DetailMonthContent(
        ISADMIN = monthViewModel.isADMIN,
        month = month,
        year = year,
        onDelete = monthViewModel::deleteOperation,
        list = opList.value)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMonthContent(
    ISADMIN:Boolean,
    month:Int,
    year:Int,
    onDelete:(op:Operation)->Boolean,
    list : List<Operation>){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(PaddingValues(12.dp))
        ,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Box{
            Row{
                Text(
                    text = "${month+1}",
                    modifier = Modifier.padding(15.dp),
                    style = MaterialTheme.typography.displayLarge,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onBackground

                    )
                Text(
                    text = "${year}", //TODO: find a better alternative
                    modifier = Modifier.padding(top = 50.dp),
                    style = MaterialTheme.typography.titleSmall,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onBackground
                    )}
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),

            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(
                items = list,
                key ={it.id}
            ){op ->
                if(ISADMIN){
                    val dismissState = rememberSwipeToDismissBoxState(
                        confirmValueChange = {
                            if(it == SwipeToDismissBoxValue.EndToStart){
                                onDelete(op)
                            }else{
                                false
                            }
                        },
                        positionalThreshold = {150f}
                    )
                    SwipeToDismissBox(state = dismissState
                        , backgroundContent = {SwipeBAckground(dismissState)},
                    ) {
                        OperationCard(op)
                    }
                }else{
                    OperationCard(op)
                }
            }
        }
    }
}



@PreviewLightDark
@Composable
fun PreviewDetail() {
    SyndicateManagerTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BgForAllScreens()
            val dummyList = ArrayList<Operation>()
            dummyList.add(Operation(id = "ref",type = "c", value = 200, user = User(name = "nisrine")))
            dummyList.add(Operation(id = "test",type = "s", value = 200, spendtype = SpendType(name = "materiel menage")))
            DetailMonthContent(true,3,2024,{false},
                dummyList
            )
        }
    }
}