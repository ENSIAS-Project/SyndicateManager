package com.ensias.syndicatemanager.models

import com.google.firebase.firestore.DocumentId
import java.util.Date

data class Operation(
    @DocumentId
    val id:String ="",
    val ref:String="",
    val type :String = "",
    val value : Long = 0,
    val date : Date = Date(),
    var spendtype :SpendType = SpendType(), //TODO : FIX IIIIT
    var user : User = User()
)
