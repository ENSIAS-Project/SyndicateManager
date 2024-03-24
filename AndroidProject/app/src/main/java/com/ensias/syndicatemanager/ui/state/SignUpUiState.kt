package com.ensias.syndicatemanager.ui.state

data class SignUpUiState(
    val prenom: String = "",
    val nom: String="",
    val password: String = "",
    val logged : Boolean = false
)
