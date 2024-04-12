package com.ensias.syndicatemanager.service.impl


import android.util.Log
import com.ensias.syndicatemanager.models.Month
import com.ensias.syndicatemanager.models.Operation
import com.ensias.syndicatemanager.models.SpendType
import com.ensias.syndicatemanager.service.DataService
import com.ensias.syndicatemanager.ui.state.ExpenseUiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class FireBaseDataService @Inject constructor(
    private val auth: FirebaseAuth,
    private val store : FirebaseFirestore,
) : DataService {
    private val MONTH_DATA_COLLECTION = "MonthData"
    private val SPEND_TYPES_COLLECTION = "spendTypes"
    private val LIST ="list"
    override val monthList: Flow<List<Month>>
        get() = auth.currentUser.run {
                store
                    .collection(MONTH_DATA_COLLECTION)
                    .orderBy("monthDate")
                    .dataObjects()
        }
    override val expensesTypes: Flow<List<SpendType>>
        get() = auth.currentUser.run {
            store
                .collection(SPEND_TYPES_COLLECTION)
                .dataObjects()
        }

    override fun getOperationsFlow(id: String): Flow<List<Operation>> {
        return store
            .collection(MONTH_DATA_COLLECTION)
            .document(id)
            .collection(LIST)
            .orderBy("date")
            .dataObjects<Operation>().catch { cause -> cause.printStackTrace() }
    }
    override fun updateMonth(m:Month, onResult: () -> Unit) {
        store.collection(MONTH_DATA_COLLECTION)
            .document(m.id)
            .set(m, SetOptions.merge())
            .addOnSuccessListener { onResult() }
            .addOnFailureListener{e-> this.onFireStoreException(e)}
    }
    override fun addMonth(month: Month): String {
        var ref = ""
        store
            .collection(MONTH_DATA_COLLECTION)
            .add(month)
            .addOnSuccessListener { docRef ->
                ref=docRef.id
            }.addOnFailureListener{
                onFireStoreException(it)
            }
        return ref
    }
    override fun addExpenseType(name:String,onResult:()->Unit) {
        val s = SpendType(name = name)
        store
            .collection(SPEND_TYPES_COLLECTION)
            .add(s)
            .addOnSuccessListener {
                onResult()
            }.addOnFailureListener{
                onFireStoreException(it)
            }
    }
    override fun updateExpenseType(id: String,newname:String,onResult:()->Unit) {
        store
            .collection(SPEND_TYPES_COLLECTION)
            .document(id)
            .update("name",newname)
            .addOnSuccessListener { onResult() }
            .addOnFailureListener{ onFireStoreException(it) }
    }
    override fun updateOperation(monthId: String,op: Operation, onResult: () -> Unit) {
        store
            .collection(MONTH_DATA_COLLECTION)
            .document(monthId)
            .collection(LIST)
            .document(op.id)
            .set(op, SetOptions.merge())
            .addOnSuccessListener {onResult()  }
            .addOnFailureListener{ onFireStoreException(it)  }
    }

    override fun addExpense(value: ExpenseUiState) {
        // get month id based on expenseuistate date
            // if found get id
            //if not ceeate a month and then add the expense to it

        // ony for debugging purposes
        Log.d("add enxpense",value.type)
    }


    override fun addOperation(monthId :String,op: Operation, onResult: (id:String) -> Unit) {
        store
            .collection(MONTH_DATA_COLLECTION)
            .document(monthId)
            .collection(LIST)
            .add(op)
            .addOnSuccessListener { docRef ->
                onResult(docRef.id)
            }.addOnFailureListener{
                onFireStoreException(it)
            }
    }

    private fun onFireStoreException(e:Exception){
        e.printStackTrace() // TODO create StoreException for each case
    }

}