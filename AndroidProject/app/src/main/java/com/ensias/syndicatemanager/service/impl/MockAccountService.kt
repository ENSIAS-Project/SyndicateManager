package com.ensias.syndicatemanager.service.impl

import com.ensias.syndicatemanager.models.LoginUiModel
import com.ensias.syndicatemanager.service.AccountService

class MockAccountService : AccountService {
    override suspend fun authenticate(login: LoginUiModel, onResult: (Throwable?) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }
}