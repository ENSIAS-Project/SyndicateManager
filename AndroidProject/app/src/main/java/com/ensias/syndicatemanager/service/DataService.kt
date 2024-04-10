package com.ensias.syndicatemanager.service

import com.ensias.syndicatemanager.models.Month
import com.ensias.syndicatemanager.models.Operation
import com.ensias.syndicatemanager.models.SpendType
import kotlinx.coroutines.flow.Flow

interface DataService {
    val monthList: Flow<List<Month>>
    val spendTypes: Flow<List<SpendType>>

    fun getOperationsFlow(id: String): Flow<List<Operation>>
    fun addMonth(month:Month):String
    fun addSpendType(spendType:SpendType,onResult:(s:String)->Unit)
    fun addOperation(monthId :String,op: Operation, onResult: (id:String) -> Unit)
    fun updateMonth(m:Month,onResult:()->Unit)
    fun updateSpendType(spendType: SpendType,onResult:()->Unit)
    fun updateOperation(monthId: String,op:Operation,onResult:()->Unit)

}

