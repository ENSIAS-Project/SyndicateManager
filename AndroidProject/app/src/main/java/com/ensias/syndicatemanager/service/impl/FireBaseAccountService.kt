package com.ensias.syndicatemanager.service.impl

import com.ensias.syndicatemanager.exceptions.AuthException
import com.ensias.syndicatemanager.exceptions.impl.DeadLineExceeded
import com.ensias.syndicatemanager.exceptions.impl.InvalidCredentialsException
import com.ensias.syndicatemanager.exceptions.impl.InvalidUserIdException
import com.ensias.syndicatemanager.exceptions.impl.MalFormatedEmailException
import com.ensias.syndicatemanager.exceptions.impl.UndefinedException
import com.ensias.syndicatemanager.exceptions.impl.UserDataMissingException
import com.ensias.syndicatemanager.models.LoginUiModel
import com.ensias.syndicatemanager.models.RegisterUiModel
import com.ensias.syndicatemanager.models.User
import com.ensias.syndicatemanager.service.AccountService
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.jvm.Throws

class FireBaseAccountService @Inject  constructor(
    private val auth: FirebaseAuth,
    private val store : FirebaseFirestore
) : AccountService {
    val TAG = "FireBaseAccountService"
    val USER_COLLECTION = "Users"
    override val userList: Flow<List<User>>
        get() = auth.currentUser.run {
            store
                .collection(USER_COLLECTION)
                .dataObjects()
        }

    @Throws(AuthException::class)
    override suspend fun authenticate(login: LoginUiModel, onResult: (User) -> Unit) {
        try {
            auth.signInWithEmailAndPassword(login.email, login.pass)
                .addOnCompleteListener { loginListerner( it, onResult) }
                .await()
        } catch (e: Exception) { throw authException(e) }
    }

    override suspend fun logout() {
        auth.signOut()
    }

    @Throws(AuthException::class)
    override fun Register(authdetails: RegisterUiModel, onResult: (User) -> Unit) {
        try {
            auth.createUserWithEmailAndPassword(authdetails.email, authdetails.password)
                .addOnCompleteListener { registerListerner(it,authdetails,onResult) }
        } catch (e: Exception) { throw authException(e) }
    }

    @Throws(AuthException::class)
    override fun reset(email: String,onResult: () -> Unit) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            resetPasswordListener(task,onResult)
        }
    }

    @Throws(AuthException::class)
    private fun resetPasswordListener(task: Task<Void>, onResult: () -> Unit) {
        if (task.isSuccessful) {
            onResult()
        }else{

        }
    }

    fun loginListerner(
        task: Task<AuthResult>,
        onResult: (User) -> Unit
    ){
        if (task.isSuccessful) {
            // Authentication successful
                getUserData(task,auth.currentUser?.uid,onResult)

        }
    }

    fun registerListerner(
        task: Task<AuthResult>,
        authdetails: RegisterUiModel,
        onResult: (User) -> Unit
    ){
        if (task.isSuccessful) {
            // Authentication successful
                setUserData(task,auth.currentUser?.uid,authdetails,onResult)
            }
        }

    @Throws(AuthException::class)
    private fun setUserData(
        task: Task<AuthResult>,
        uid: String?,
        authdetails: RegisterUiModel,
        onResult: (User) -> Unit
    ) {

        if(uid==null){
            throw InvalidUserIdException()
        }else{
            var dat  = User(IS_ADMIN = false, name = authdetails.prenom, email = authdetails.email, familyname = authdetails.nom,id = uid)
            store.collection(USER_COLLECTION)
                .document(uid)
                .set(dat, SetOptions.merge())
                .addOnSuccessListener { onResult(dat) }
                .addOnFailureListener{e -> onFirestoreEception(e) }
        }
    }

    private fun authException(e:Exception) :AuthException{
        if(e is FirebaseAuthInvalidCredentialsException){
            if(e.message.equals("The email address is badly formatted.")){
                return MalFormatedEmailException()
            }else{
                return InvalidCredentialsException()
            }
        }
        if(e is AuthException)
            return e
        else return UndefinedException()
    }
    @Throws(AuthException::class)
    fun getUserData(task: Task<AuthResult>, uid: String?, onResult: (User) -> Unit){
        if(uid == null){
            throw InvalidUserIdException()
        }else{
            store.collection(USER_COLLECTION)
                .document(uid)
                .get()
                .addOnSuccessListener { document-> ongetUserDataSucessListener(document,onResult) }
                .addOnFailureListener{e -> onFirestoreEception(e) }
        }
    }

    @Throws(AuthException::class)
    fun ongetUserDataSucessListener(
        document: DocumentSnapshot,
        onResult: (User) -> Unit
    ) {
        val user = document.toObject<User>()
        if(user == null){
            throw UserDataMissingException()
        }else{
            onResult(user)
        }
    }

    @Throws(AuthException::class)
    fun onFirestoreEception(e: java.lang.Exception){
        if(e is FirebaseFirestoreException){
            if(e.code == FirebaseFirestoreException.Code.DEADLINE_EXCEEDED){
                throw DeadLineExceeded()
            }else{
                throw UndefinedException()
            }
        }
    }
}