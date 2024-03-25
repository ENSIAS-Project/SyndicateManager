package com.ensias.syndicatemanager.ui.state

data class RegisterUiState(
    val prenom: String = "",
    val nom: String="",
    val email: String ="",
    val password: String = "",
    val confirmpass: String = "",
    val validmail : Boolean = true
)
