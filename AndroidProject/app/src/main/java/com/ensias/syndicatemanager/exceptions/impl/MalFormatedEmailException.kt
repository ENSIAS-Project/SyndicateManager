package com.ensias.syndicatemanager.exceptions.impl

import com.ensias.syndicatemanager.R
import com.ensias.syndicatemanager.exceptions.AuthException

class MalFormatedEmailException : AuthException() {
    override fun getmessage(): Int {
        return (R.string.INVALID_EMAIL_EXCEPTION)
    }

}