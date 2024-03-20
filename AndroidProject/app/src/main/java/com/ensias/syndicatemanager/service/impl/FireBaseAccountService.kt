package com.ensias.syndicatemanager.service.impl

import android.util.Log
import com.ensias.syndicatemanager.Exceptions.AuthException
import com.ensias.syndicatemanager.models.LoginUiModel
import com.ensias.syndicatemanager.models.User
import com.ensias.syndicatemanager.service.AccountService
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireBaseAccountService @Inject  constructor(
    private val auth: FirebaseAuth,
    private val store : FirebaseFirestore
) : AccountService {
    val TAG = "FireBaseAccountService"
    val USER_COLLECTION = "Users"

    override suspend fun authenticate(login : LoginUiModel,onResult: (t:AuthException?) -> Unit) {
        try {
            auth.signInWithEmailAndPassword(login.email, login.pass)
                .addOnCompleteListener { authListerner(it,onResult) }
                .await()
        } catch (e: Exception) { authException(e,onResult) }
    }

    override suspend fun logout() {
        auth.signOut()
    }

    fun authListerner(task: Task<AuthResult>,onResult: (t:AuthException?) -> Unit){
        if (task.isSuccessful) {
            // Authentication successful
            // get or set firestore
            getUserData(task,auth.currentUser?.uid,onResult)
        }
    }

    fun authException(e:Exception,onResult: (t:AuthException?) -> Unit){
        if(e is FirebaseAuthInvalidCredentialsException)
            onResult(AuthException.INVALID_CREDENTIAL)
        else onResult(AuthException.OTHER_ERROR)
    }

    fun getUserData(task: Task<AuthResult>, uid: String?, onResult: (t: AuthException?) -> Unit){
        if(uid == null){
            onResult(AuthException.INVALID_UID)
        }else{
            // TODO: remove snippets used to set the adminUser for the first time
            //var dat : User = User(IS_ADMIN = true, name = "administrateur", familyname = "elb")
            store.collection(USER_COLLECTION)
                .document(uid)
                .get()
                //.set(dat, SetOptions.merge())
                .addOnSuccessListener { document-> ongetUserDataSucessListener(document,onResult) }
                .addOnFailureListener{e -> onFirestoreEception(e,onResult) }
        }
    }

    fun ongetUserDataSucessListener(
        document: DocumentSnapshot,
        onResult: (t: AuthException?) -> Unit
    ) {
        if(document == null){
            onResult(AuthException.EMPTY_REPLY)
        }else{
            // TODO : read user data
            val user = document.toObject<User>()
            Log.d(TAG,user.toString())
        }
    }

    fun onFirestoreEception(e: java.lang.Exception, onResult: (t: AuthException?) -> Unit){
        if(e is FirebaseFirestoreException){
            if(e.code == FirebaseFirestoreException.Code.DEADLINE_EXCEEDED){
                onResult(AuthException.TIMEOUT)
                // TODO: better handling errors for better clarity to the endUser
            }else{
                onResult(AuthException.OTHER_ERROR)
            }
        }
    }
}