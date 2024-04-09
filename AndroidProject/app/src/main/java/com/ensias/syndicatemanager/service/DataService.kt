package com.ensias.syndicatemanager.service

import com.ensias.syndicatemanager.models.Month
import kotlin.jvm.Throws

interface DataService {
    @Throws(Exception::class)
    suspend fun addTempData(month: Month)
}

