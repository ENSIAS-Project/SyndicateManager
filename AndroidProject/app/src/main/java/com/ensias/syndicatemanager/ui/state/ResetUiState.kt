package com.ensias.syndicatemanager.ui.state

data class ResetUiState(
    val email : String = "",
    val isMailValid : Boolean = true
)