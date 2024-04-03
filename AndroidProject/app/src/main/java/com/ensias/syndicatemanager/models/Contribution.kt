package com.ensias.syndicatemanager.models

import java.util.Date

data class Contribution (
    override val value: Float,
    override val date: Date,
    val user : User,
): Operation()