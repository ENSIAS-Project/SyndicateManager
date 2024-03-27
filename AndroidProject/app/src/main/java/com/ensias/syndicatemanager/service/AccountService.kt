package com.ensias.syndicatemanager.service

import com.ensias.syndicatemanager.exceptions.AuthException
import com.ensias.syndicatemanager.models.LoginUiModel
import com.ensias.syndicatemanager.models.RegisterUiModel
import com.ensias.syndicatemanager.models.User
import kotlin.jvm.Throws

interface AccountService {
    @Throws(AuthException::class)
    suspend fun authenticate(login : LoginUiModel, onResult: (User) -> Unit)

    suspend fun logout()
    @Throws(AuthException::class)
    abstract fun Register(Register: RegisterUiModel, onResult: (User) -> Unit)
    @Throws(AuthException::class)
    abstract fun reset(email: String,onResult: () -> Unit)

}