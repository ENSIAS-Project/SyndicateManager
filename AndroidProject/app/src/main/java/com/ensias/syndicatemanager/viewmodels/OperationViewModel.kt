package com.ensias.syndicatemanager.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ensias.syndicatemanager.R
import com.ensias.syndicatemanager.exceptions.impl.NotCurrentMonthException
import com.ensias.syndicatemanager.models.Operation
import com.ensias.syndicatemanager.models.User
import com.ensias.syndicatemanager.service.AccountService
import com.ensias.syndicatemanager.service.DataService
import com.ensias.syndicatemanager.ui.state.ContributionUiState
import com.ensias.syndicatemanager.ui.state.ExpenseUiState
import com.ensias.syndicatemanager.ui.view.SnackbarManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OperationViewModel @Inject constructor(
    private val dataService: DataService

) : ViewModel(){
    private val CONTRIBUTION = "c"
    private val EXPENSE = "s"
    val users = dataService.users
    val contribiutionUiState = mutableStateOf(ContributionUiState())
    var expenseUiState = mutableStateOf(ExpenseUiState())
    val expensesTypes = dataService.expensesTypes
    fun addExpense() {
        viewModelScope.launch {
            expenseUiState.value = expenseUiState.value.copy(pendingOperation = true)
            val op = Operation(
                date = expenseUiState.value.date,
                ref = expenseUiState.value.ref,
                type = EXPENSE,
                value = expenseUiState.value.amount.toLong(),
            )
            try {
                dataService.addOperation(op) { addexpenseResult() }
            }catch (e:NotCurrentMonthException){
                SnackbarManager.showMessage(e.getmessage())
            }
        }
    }

    private fun addexpenseResult() {
        expenseUiState.value = expenseUiState.value.copy(pendingOperation = false )
        SnackbarManager.showMessage(R.string.OPERATION_SUCESSFUL)
    }

    private fun addcontributionResult():Unit {
        contribiutionUiState.value = contribiutionUiState.value.copy(pendingOperation = false )
        SnackbarManager.showMessage(R.string.OPERATION_SUCESSFUL)
    }


    fun addExpenseType(id: String) {
        dataService.addExpenseType(id){
            SnackbarManager.showMessage(R.string.TYPE_DE_DEPENSE_AJOUTE) //TODO: transfer to a String
        }
    }
    fun modifyExpenseType(id: String, name: String) {
        dataService.updateExpenseType(id,name){
            SnackbarManager.showMessage(R.string.TYPE_DE_DEPENSE_MIS_A_JOUR) //TODO: transfer to a String
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

    fun onContribValueChange(newval: String) {
        val int = newval.toIntOrNull()
        if(int is Int){
            contribiutionUiState.value = contribiutionUiState.value.copy(amount = int)
        }else{
            contribiutionUiState.value = contribiutionUiState.value.copy(amount = 0)
        }
    }

    fun addContribution() {
        viewModelScope.launch {
            contribiutionUiState.value = contribiutionUiState.value.copy(pendingOperation = true)
            val op = Operation(
                date = contribiutionUiState.value.date,
                ref = contribiutionUiState.value.user.id,
                type = CONTRIBUTION,
                value = contribiutionUiState.value.amount.toLong(),
            )
            try {
                dataService.addOperation(op) { addcontributionResult() }
            }catch (e:NotCurrentMonthException){
                SnackbarManager.showMessage(e.getmessage())
            }
        }
    }


}