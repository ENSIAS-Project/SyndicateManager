package com.ensias.syndicatemanager.exceptions.impl

import com.ensias.syndicatemanager.R
import com.ensias.syndicatemanager.exceptions.DataServiceExceptions

class NotCurrentMonthException: DataServiceExceptions() {
    override fun getmessage(): Int {
       return R.string.DATE_MISMATCH_EXCEPTRION
    }
}