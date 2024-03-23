package com.ensias.syndicatemanager.service

import com.ensias.syndicatemanager.exceptions.AuthException
import com.ensias.syndicatemanager.models.LoginUiModel
import com.ensias.syndicatemanager.models.User
import kotlin.jvm.Throws

interface AccountService {
    @Throws(AuthException::class)
    suspend fun authenticate(login : LoginUiModel, onResult: (User) -> Unit)

    suspend fun logout()
    abstract fun SignUp(loginUiModel: LoginUiModel, onResult: (User) -> Unit)

}