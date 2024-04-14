package com.ensias.syndicatemanager.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ensias.syndicatemanager.service.AccountService
import com.ensias.syndicatemanager.service.DataService
import com.ensias.syndicatemanager.ui.state.ExpenseUiState
import com.ensias.syndicatemanager.ui.view.SnackbarManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OperationViewModel @Inject constructor(
    private val accountService : AccountService,
    private val dataService: DataService

) : ViewModel(){
    var expenseUiState = mutableStateOf(ExpenseUiState())
    val expensesTypes = dataService.expensesTypes
    fun addExpense() {
        viewModelScope.launch {
            dataService.addExpense(expenseUiState.value)
           {
            SnackbarManager.showMessage("Expense added")
            }
        }

    }
    fun addExpenseType(id: String) {
        dataService.addExpenseType(id){
            SnackbarManager.showMessage("data created") //TODO: transfer to a String
        }
    }
    fun modifyExpenseType(id: String, name: String) {
        dataService.updateExpenseType(id,name){
            SnackbarManager.showMessage("data updated") //TODO: transfer to a String
        }
    }


    fun setNewVal(newVal: String) {
        val int =newVal.toIntOrNull()
        if(int is Int){
            expenseUiState.value = expenseUiState.value.copy(amount = int )
        }else{
            expenseUiState.value = expenseUiState.value.copy(amount = 0)
        }
    }



}