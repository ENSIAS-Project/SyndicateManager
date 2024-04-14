package com.ensias.syndicatemanager.service

import com.ensias.syndicatemanager.models.Month
import com.ensias.syndicatemanager.models.Operation
import com.ensias.syndicatemanager.models.SpendType
import com.ensias.syndicatemanager.ui.state.ExpenseUiState
import kotlinx.coroutines.flow.Flow
import java.util.Calendar

interface DataService {
    abstract val expensesTypes: Flow<List<SpendType>>
    val monthList: Flow<List<Month>>
    fun getOperationsFlow(id: String): Flow<List<Operation>>
    fun addMonth(month: Month): String
    fun addExpenseType(name: String, onResult: () -> Unit)

    //fun addOperation(monthId :String,op: Operation, onResult: (id:String) -> Unit)
    fun updateMonth(m: Month, onResult: () -> Unit)
    fun updateExpenseType(id: String, newname: String, onResult: () -> Unit)
    suspend fun  updateOperation(monthId: String, op: Operation, onResult: () -> Unit)
    suspend fun  addExpense(value: ExpenseUiState,onResult: () -> Unit): Flow<List<Operation>>

}
