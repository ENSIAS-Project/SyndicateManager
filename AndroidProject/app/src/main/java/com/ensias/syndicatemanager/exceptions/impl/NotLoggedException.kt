package com.ensias.syndicatemanager.exceptions.impl

import com.ensias.syndicatemanager.R
import com.ensias.syndicatemanager.exceptions.DataServiceExceptions

class NotLoggedException : DataServiceExceptions() {
    override fun getmessage(): Int {
        return (R.string.NOT_LOGGED_EXCEPTION)
    }
}