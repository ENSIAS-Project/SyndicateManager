package com.ensias.syndicatemanager

const val AUTH:String = "auth"
const val LOGIN:String = "login"
const val RESET_PASSWORD:String = "resetPassword"
const val MAIN:String = "main"
const val MONTH_VIEW:String = "month-view"
const val MONTH_ID:String = "monthID"
const val MONTH_DETAILS:String = "month-details/{$MONTH_ID}"
const val SIGNUP:String = "signup"

fun MONTHDETAILS(id:String): String {
    return "month-details/$id"
}