package com.ensias.syndicatemanager.ui.state

import java.util.Date

data class ContributionUiState(
    var user :String ="",
    var date : Date = Date(),
    var amount : Int=0
)
