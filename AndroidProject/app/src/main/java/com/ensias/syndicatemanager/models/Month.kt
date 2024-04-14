package com.ensias.syndicatemanager.models

import com.google.firebase.firestore.DocumentId
import java.util.Date

data class Month (
    @DocumentId
    val id :String = "",
    val prevBalance : Long = 0,
    var currBalance: Long = 0,
    val monthDate : Date = Date(),
    val debit : Long = 0,
    val credit : Long = 0,
)