package com.ensias.syndicatemanager.exceptions

abstract class AuthException : Exception() {
    abstract fun getmessage() : Int

}