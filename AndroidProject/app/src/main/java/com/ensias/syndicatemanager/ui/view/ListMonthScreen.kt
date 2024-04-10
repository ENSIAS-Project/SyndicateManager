package com.ensias.syndicatemanager.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ensias.syndicatemanager.models.Month
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import com.ensias.syndicatemanager.ui.view.components.BgForAllScreens
import com.ensias.syndicatemanager.ui.view.components.MonthCard
import com.ensias.syndicatemanager.viewmodels.MonthViewModel


@Composable
fun ListMonthScreen(
    open:(String) -> Unit,
    contentPadding: PaddingValues,
    monthViewModel: MonthViewModel = hiltViewModel()
) {
    val monthlist = monthViewModel.monthList
        .collectAsStateWithLifecycle(emptyList())
        //.collectAsState(emptyList())
   ListMonthContent(
       monthlist= monthlist.value,
       contentPadding= contentPadding,
       clickedMonth = {id -> monthViewModel.onMonthSelect(id,open)}
   )
}

@Composable
fun ListMonthContent(
    monthlist : List<Month>,
    contentPadding: PaddingValues,
    clickedMonth:(id:String)-> Unit
    ){
    BgForAllScreens()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(contentPadding)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),

            contentPadding = contentPadding
        ) {
            items(
                monthlist, key ={it.id}
            ){month ->
                MonthCard(month,clickedMonth)
            }

        }
    }
}

@PreviewLightDark

@Composable
fun PreviewListMonth() {
    SyndicateManagerTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val dummylist =ArrayList<Month>()
            dummylist.add(Month())
            dummylist.add(Month(id="ee"))
            ListMonthContent(
                dummylist,
                PaddingValues(10.dp),
                {}
                )
        }
    }
}