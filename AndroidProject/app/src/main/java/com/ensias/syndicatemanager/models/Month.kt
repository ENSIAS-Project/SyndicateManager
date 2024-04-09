package com.ensias.syndicatemanager.models

import java.util.Date

data class Month (
    val prevBalalnce : Int,
    val monthDate : Date,
    val operations: List<Operation>
)