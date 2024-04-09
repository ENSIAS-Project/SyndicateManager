package com.ensias.syndicatemanager.models

import java.util.Date

abstract class Operation {
    abstract val value: Float
    abstract val date: Date
}