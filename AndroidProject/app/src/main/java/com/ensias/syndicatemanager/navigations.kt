package com.ensias.syndicatemanager

const val AUTH:String = "auth"
const val LOGIN:String = "login"
const val RESET_PASSWORD:String = "resetPassword"
const val MAIN:String = "main"
const val MONTH_VIEW:String = "month-view"
const val MONTH_ID:String = "monthID"
const val MONTH_VAL:String = "monthval"
const val YEAR_VAL:String = "yearval"
const val MONTH_DETAILS:String = "month-details/{$MONTH_ID}/{$MONTH_VAL}/{$YEAR_VAL}"
const val SIGNUP:String = "signup"

fun MONTHDETAILS(id:String,m:Int,y:Int): String {
    return "month-details/$id/$m/$y"
}