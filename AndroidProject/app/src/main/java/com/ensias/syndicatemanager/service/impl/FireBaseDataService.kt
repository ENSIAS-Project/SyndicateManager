package com.ensias.syndicatemanager.service.impl

import android.util.Log
import com.ensias.syndicatemanager.models.Month
import com.ensias.syndicatemanager.service.AccountService
import com.ensias.syndicatemanager.service.DataService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import javax.inject.Inject

class FireBaseDataService @Inject constructor(
    private val auth: FirebaseAuth,
    private val store : FirebaseFirestore
) : DataService {
    val DATA_COLLECTION = "data"
    override suspend fun addTempData(month: Month) {
        store.collection(DATA_COLLECTION)
            .document("test")
            .set(month, SetOptions.merge())
            .addOnSuccessListener { Log.d("FireBaseDataService","DONE") }
            .addOnFailureListener{e-> Log.d("FireBaseDataService","ERROR") }
    }

}