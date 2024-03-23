package com.ensias.syndicatemanager.exceptions.impl

import com.ensias.syndicatemanager.exceptions.AuthException
import com.ensias.syndicatemanager.R

class UndefinedException : AuthException() {
    override fun getmessage(): Int {
        return (R.string.UNDEFINED_ERROR)
    }

}