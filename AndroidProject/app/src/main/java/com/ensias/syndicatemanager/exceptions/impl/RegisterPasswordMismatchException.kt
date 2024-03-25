package com.ensias.syndicatemanager.exceptions.impl

import android.content.res.Resources
import com.ensias.syndicatemanager.exceptions.AuthException
import com.ensias.syndicatemanager.R

class RegisterPasswordMismatchException : AuthException() {
    override fun getmessage(): Int {
        return (R.string.ERROR_DIFFERENT_PASSWORDS)
    }

}