package com.ensias.syndicatemanager.service

import com.ensias.syndicatemanager.models.Month
import com.ensias.syndicatemanager.models.Operation
import com.ensias.syndicatemanager.models.SpendType
import com.ensias.syndicatemanager.models.User
import com.ensias.syndicatemanager.ui.state.ExpenseUiState
import kotlinx.coroutines.flow.Flow
import java.util.Calendar

interface DataService {
    abstract val users: Flow<List<User>>
    abstract val expensesTypes: Flow<List<SpendType>>
    val monthList: Flow<List<Month>>
    fun getOperationsFlow(id: String): Flow<List<Operation>>

    fun addExpenseType(name: String, onResult: () -> Unit)

    //fun addOperation(monthId :String,op: Operation, onResult: (id:String) -> Unit)
    fun updateExpenseType(id: String, newname: String, onResult: () -> Unit)

    suspend fun addOperation (op :Operation, onResult: () -> Unit)

    suspend fun removeOperation(op: Operation, onResult: ()->Unit)
    abstract fun checkCurrentMonth(op: Operation): Boolean


}
