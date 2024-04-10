package com.ensias.syndicatemanager.ui.state

import java.util.Date

data class ExpenseUiState (
    var type :String ="",
    var date : Date =Date(),
    var amount : Int=0
)