package com.ensias.syndicatemanager.service

import com.ensias.syndicatemanager.models.LoginUiModel

interface AccountService {
    suspend fun authenticate(login : LoginUiModel,onResult: (Throwable?) -> Unit)

    suspend fun logout()

}