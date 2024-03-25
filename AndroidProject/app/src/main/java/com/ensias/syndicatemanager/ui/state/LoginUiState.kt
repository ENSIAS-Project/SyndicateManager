package com.ensias.syndicatemanager.ui.state

data class LoginUiState (
    val email: String = "",
    val password: String = "",
    val logging:Boolean = false,
    val validmail:Boolean = true
)