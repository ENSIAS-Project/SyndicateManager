package com.ensias.syndicatemanager.exceptions

 abstract class DataServiceExceptions : Exception() {
     abstract fun getmessage() : Int
}