package com.ensias.syndicatemanager.ui.state

data class MonthCardUIState (
    val month:Long,
    val year:Long,
    val income:Long,
    val outcome:Long,
    val previousBalance:Long,
    val balance:Long
)