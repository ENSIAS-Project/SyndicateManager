package com.ensias.syndicatemanager.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ensias.syndicatemanager.models.SpendType
import com.ensias.syndicatemanager.service.AccountService
import com.ensias.syndicatemanager.service.DataService
import com.ensias.syndicatemanager.ui.state.ExpenseUiState
import com.ensias.syndicatemanager.ui.view.SnackbarManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class OperationViewModel @Inject constructor(
    private val accountService : AccountService,
    private val dataService: DataService

) : ViewModel(){
    var expenseUiState: MutableState<ExpenseUiState> = mutableStateOf(ExpenseUiState())
    val expensesTypes = dataService.expensesTypes
    fun addExpense() {

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
        expenseUiState.value.copy(amount = newVal.toInt())
    }



}