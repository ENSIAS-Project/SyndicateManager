package com.ensias.syndicatemanager.exceptions.impl

import com.ensias.syndicatemanager.exceptions.AuthException
import com.ensias.syndicatemanager.R

class UserDataMissingException : AuthException() {
    override fun getmessage(): Int {
        return (R.string.USER_DATA_MISSING_EXCEPTION)
    }

}