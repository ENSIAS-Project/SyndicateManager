package com.ensias.syndicatemanager.models

import com.google.firebase.firestore.DocumentId
import java.util.Date

data class Month (
    @DocumentId
    var id :String = "",
    val prevBalance : Long = 0,
    var currBalance: Long = 0,
    val monthDate : Date = Date(),
    var debit : Long = 0,
    var credit : Long = 0,
)