package com.ensias.syndicatemanager.service.impl

import android.util.Log
import com.ensias.syndicatemanager.di.Repo
import com.ensias.syndicatemanager.exceptions.AuthException
import com.ensias.syndicatemanager.exceptions.impl.NotLoggedException
import com.ensias.syndicatemanager.models.Month
import com.ensias.syndicatemanager.models.Operation
import com.ensias.syndicatemanager.models.SpendType
import com.ensias.syndicatemanager.service.AccountService
import com.ensias.syndicatemanager.service.DataService
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import java.util.Calendar
import java.util.Date
import java.util.LinkedList
import javax.inject.Inject
import kotlin.jvm.Throws

class FireBaseDataService @Inject constructor(
    private val auth: FirebaseAuth,
    private val store : FirebaseFirestore,
) : DataService {

    val MONTH_DATA_COLLECTION = "MonthData"
    val SPEND_TYPES_COLLECTION = "spendTypes"
    val LIST ="list"

    override val monthList: Flow<List<Month>>
        get() = auth.currentUser.run {
                store
                    .collection(MONTH_DATA_COLLECTION)
                    .orderBy("monthDate")
                    .dataObjects()
        }

    val spendTypes: Flow<List<SpendType>>
        get() = auth.currentUser.run {
            store
                .collection(SPEND_TYPES_COLLECTION)
                .orderBy("Date")
                .dataObjects()
        }


    override suspend fun addTempData(month: Month) {
      //  store.collection(DATA_COLLECTION)
        //    .document("test")
         //   .set(month, SetOptions.merge())
        //    .addOnSuccessListener { Log.d("FireBaseDataService","DONE") }
         //   .addOnFailureListener{e-> Log.d("FireBaseDataService","ERROR") }
    }

    override fun getOperationsFlow(id: String): Flow<List<Operation>> {
        return store
            .collection(MONTH_DATA_COLLECTION)
            .document(id)
            .collection(LIST)
            .orderBy("date")
            .dataObjects<Operation>().catch { cause -> cause.printStackTrace() }
    }

}