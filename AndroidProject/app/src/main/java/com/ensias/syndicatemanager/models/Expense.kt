package com.ensias.syndicatemanager.models

import java.util.Date

data class Expense (
    override val value: Float,
    override val date: Date,
    val type : ExpenseType,
): Operation()


