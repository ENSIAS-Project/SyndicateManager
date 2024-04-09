package com.ensias.syndicatemanager.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ensias.syndicatemanager.di.Repo
import com.ensias.syndicatemanager.models.Contribution
import com.ensias.syndicatemanager.models.Expense
import com.ensias.syndicatemanager.models.ExpenseType
import com.ensias.syndicatemanager.models.Month
import com.ensias.syndicatemanager.models.Operation
import com.ensias.syndicatemanager.service.DataService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.LinkedList
import javax.inject.Inject

@HiltViewModel
class MonthViewModel @Inject constructor(
    private val dataService: DataService,
    ):ViewModel(){
    fun getData(){

    }
    fun showMonthDetail(){

    }
    fun tempimpldata(){
        // setting some fake data
        val operations : LinkedList<Operation> = LinkedList()
        val exptype :ExpenseType = ExpenseType("electricite")
        val exp : Expense = Expense(
            value= 120f,
            date = Calendar.getInstance().time,
            type = exptype
            )
        operations.add(exp)
        val contrib : Contribution = Contribution(
            value = 170f,
            date = Calendar.getInstance().time,
            user = Repo.user
        )
        operations.add(contrib)
        val month: Month = Month(
            prevBalalnce = 120,
            monthDate = Calendar.getInstance().time,
            operations = operations
        )
        viewModelScope.launch {
            dataService.addTempData(month)
        }
    }
}