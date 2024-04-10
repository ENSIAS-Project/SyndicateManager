package com.ensias.syndicatemanager.models

import com.google.firebase.firestore.DocumentId

data class SpendType (
    @DocumentId
    val id:String = "",
    val name:String = ""
)