package com.ensias.syndicatemanager.service

import com.ensias.syndicatemanager.models.Month
import com.ensias.syndicatemanager.models.Operation
import kotlinx.coroutines.flow.Flow
import kotlin.jvm.Throws

interface DataService {
    val monthList: Flow<List<Month>>

    @Throws(Exception::class)
    suspend fun addTempData(month: Month)
    fun getOperationsFlow(id: String): Flow<List<Operation>>

}

