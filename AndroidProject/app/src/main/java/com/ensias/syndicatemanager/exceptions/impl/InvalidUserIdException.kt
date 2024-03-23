package com.ensias.syndicatemanager.exceptions.impl

import com.ensias.syndicatemanager.exceptions.AuthException
import com.ensias.syndicatemanager.R

class InvalidUserIdException : AuthException() {
    override fun getmessage(): Int {
        return (R.string.INVALID_USER_ID)
    }

}