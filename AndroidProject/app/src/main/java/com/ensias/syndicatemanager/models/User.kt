package com.ensias.syndicatemanager.models

data class User (
    // val UID         : String,  // UID WILL BE GIVEN BY FIREBASE AUTH AND IT IS THE NAME OF THE DOCUMENT
    @field:JvmField // use this annotation if your Boolean field is prefixed with 'is'
    //SEE :  https://firebase.google.com/docs/firestore/manage-data/add-data#custom_objects
    var IS_ADMIN    : Boolean   = false,
    val name        : String    = "",
    val familyname  : String    = "",
    val id          : String    = "",
    val email       : String    = ""
){
    override fun toString(): String {
        return super.toString()+"ID $id IS_ADMIN $IS_ADMIN name : $name  fname : $familyname"
    }
}
