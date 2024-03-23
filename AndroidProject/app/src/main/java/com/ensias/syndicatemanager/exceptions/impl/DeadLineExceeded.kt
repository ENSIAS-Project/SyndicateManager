package com.ensias.syndicatemanager.exceptions.impl

import android.content.res.Resources
import com.ensias.syndicatemanager.exceptions.AuthException
import com.ensias.syndicatemanager.R

class DeadLineExceeded : AuthException() {
    override fun getmessage(): Int {
        return (R.string.DEAD_LINE_EXCEEDED)
    }

}