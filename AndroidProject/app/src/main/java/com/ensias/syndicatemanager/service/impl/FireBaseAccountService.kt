package com.ensias.syndicatemanager.service.impl

import android.util.Log
import com.ensias.syndicatemanager.models.LoginUiModel
import com.ensias.syndicatemanager.service.AccountService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireBaseAccountService @Inject  constructor(private val auth: FirebaseAuth) : AccountService {
    override suspend fun authenticate(login : LoginUiModel,onResult: (Throwable?) -> Unit) {
        Log.d("FirebaseAccountService","start logging")
        auth.signInWithEmailAndPassword(login.email, login.pass).
            addOnCompleteListener { onResult(it.exception) }.
        await()
        Log.d("FirebaseAccountService","logged")
    }

    override suspend fun logout() {
        auth.signOut()
    }

}