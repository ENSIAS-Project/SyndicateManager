package com.ensias.syndicatemanager.ui.state

import com.ensias.syndicatemanager.models.User
import java.util.Date

data class ContributionUiState(
    var user :User = User(),
    var date : Date = Date(),
    var amount : Int=0,
    val pendingOperation: Boolean = false
)