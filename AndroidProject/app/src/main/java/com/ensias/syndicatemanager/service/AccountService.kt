package com.ensias.syndicatemanager.service

import com.ensias.syndicatemanager.Exceptions.AuthException
import com.ensias.syndicatemanager.models.LoginUiModel

interface AccountService {
    suspend fun authenticate(login : LoginUiModel,onResult: (AuthException?) -> Unit)

    suspend fun logout()

}