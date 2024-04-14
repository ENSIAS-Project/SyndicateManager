package com.ensias.syndicatemanager.service.impl


import com.ensias.syndicatemanager.models.Month
import com.ensias.syndicatemanager.models.Operation
import com.ensias.syndicatemanager.models.SpendType
import com.ensias.syndicatemanager.models.User
import com.ensias.syndicatemanager.service.DataService
import com.ensias.syndicatemanager.ui.state.ExpenseUiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireBaseDataService @Inject constructor(
    private val auth: FirebaseAuth,
    private val store : FirebaseFirestore,
) : DataService {
    private val MONTH_DATA_COLLECTION = "MonthData"
    private val SPEND_TYPES_COLLECTION = "spendTypes"
    private val MONTHDATE = "monthDate"
    private val LIST = "list"
    private val USERS = "Users"
    private val EXPENSE = "s"
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

     return flow {
         val s = store.collection(MONTH_DATA_COLLECTION)
             .document(id)
             .collection(LIST)
             .orderBy("date")
             .get()
             .await()
            val operations = mutableListOf<Operation>()
         for (doc in s){
             val operation :Operation
             val idop = doc.id
             val type = doc.getString("type")?:continue
             val ref = doc.getString("ref") ?:continue
             val value = doc.getLong("value")?:continue

             if(type ==EXPENSE){
                 val spendType = getSpendType(ref);
                 operation = Operation(id = idop,ref,type = type, value = value, spendtype = spendType)
             }else{
                 val user = getUser(ref);
                 operation = Operation(id = idop,ref,type = type, value = value, user = user)
             }
            operations.add(operation)
         }
         emit(operations)
     }
    }
    suspend fun getSpendType(id:String):SpendType{
        val document = store.collection(SPEND_TYPES_COLLECTION).document(id).get().await()
        val name = document.getString("name") ?: ""
        return SpendType(id = id,name = name)
    }

    suspend fun getUser(id:String):User{
        val document = store.collection(USERS).document(id).get().await()
        val name = document.getString("name") ?: ""
        return User(id = id,name = name)
    }

    override fun updateMonth(m: Month, onResult: () -> Unit) {
        store.collection(MONTH_DATA_COLLECTION)
            .document(m.id)
            .set(m, SetOptions.merge())
            .addOnSuccessListener { onResult() }
            .addOnFailureListener { throw it }
    }

    override fun addMonth(month: Month): String {
        var ref = ""
        store
            .collection(MONTH_DATA_COLLECTION)
            .add(month)
            .addOnSuccessListener { docRef ->
                ref = docRef.id
            }.addOnFailureListener {
                throw it
            }
        return ref
    }

    override fun addExpenseType(name: String, onResult: () -> Unit) {
        val s = SpendType(name = name)
        store
            .collection(SPEND_TYPES_COLLECTION)
            .add(s)
            .addOnSuccessListener {
                onResult()
            }.addOnFailureListener {
                throw it
            }
    }

    override fun updateExpenseType(id: String, newname: String, onResult: () -> Unit) {
        store
            .collection(SPEND_TYPES_COLLECTION)
            .document(id)
            .update("name", newname)
            .addOnSuccessListener { onResult() }
            .addOnFailureListener { throw it }
    }

    override suspend fun updateOperation(monthId: String, op: Operation, onResult: () -> Unit) {
        //TODO: update th op and all it refs on the list
        store
            .collection(MONTH_DATA_COLLECTION)
            .document(monthId)
            .collection(LIST)
            .document(op.id)
            .set(op, SetOptions.merge())
            .addOnSuccessListener {
                // update month data
               // updateMonth(op = op,
                 //   month = getMonth(monthId),
                   // onResult = onResult,
                    //ref = monthId)
            }
            //.addOnFailureListener { throw it }
    }

    override suspend fun addExpense(value: ExpenseUiState, onResult: () -> Unit) : Flow<List<Operation>> = flow {
        val operationsCollection = store.collection("/MonthData/9YjpA1jGL6B6KjNUEPhd/list")
        val operationsSnapshot = operationsCollection.get().await()
        val operations = mutableListOf<Operation>()
        for (document in operationsSnapshot.documents) {
            val operationId = document.id
            val refId = document.getString("ref") ?: continue // Assuming userId field exists
            val value = document.getLong("value") ?: continue // Assuming value field exists

            val user = getopTypeById(refId) // Fetch corresponding user object
            user.toString()
            val operation = Operation(operationId, refId)
            operations.add(operation)
        }
        emit(operations)
    }
    private suspend fun getopTypeById(userId: String): SpendType {
        val userDocument = store.collection("users").document(userId).get().await()
        val id = userDocument.id
        val name = userDocument.getString("name") ?: ""
        return SpendType(id,name)
    }


    /*private suspend fun getMonth(id:String):Month{
        val t = store
            .collection(MONTH_DATA_COLLECTION)
            .document(id)
            .get()
            .await()
            return t.toObject<Month>()!!
    }*/

   /* private suspend fun addExpenseToMonth(op: Operation,month:Month, ref: String,onResult: () -> Unit) {
        //add operation to database
        store.collection(MONTH_DATA_COLLECTION)
            .document(ref)
            .collection(LIST)
            .add(op)
            .addOnSuccessListener { //updateMonth(op,month,ref,onResult)
                 }
            .addOnFailureListener{
               throw it
            }
    }*/

    /*private suspend fun updateMonth(op: Operation,month:Month, ref: String,onResult: () -> Unit){
        if(op.type==EXPENSE){
            month.currBalance -= op.value
        }else{
            month.currBalance += op.value
        }
        store
            .collection(MONTH_DATA_COLLECTION)
            .document(ref)
            .set(month, SetOptions.merge()).addOnSuccessListener { onResult() }
    }*/





    /*private suspend fun getMonthId(date: Date): String {
        val cal = Calendar.getInstance()
        cal.time = date
        cal.set(Calendar.DAY_OF_MONTH, 1)
        cal.set(Calendar.HOUR, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)

        val querySnapshot = store.collection(MONTH_DATA_COLLECTION)
            .whereEqualTo(MONTHDATE, cal.time)
            .get()
            .await() // Wait for the query to complete

        if (querySnapshot.isEmpty) {
            // Handle case where no documents are returned
            return ""
        }
        // Get the first document's ID
        return querySnapshot.documents[0].id
    }*/




    /*private suspend fun createNewMonthInstance(date:Date):Month{
        val lastMonth = getLastmonthData()
        val cal = Calendar.getInstance()
        cal.time = date
        cal.set(Calendar.DAY_OF_MONTH, 1)
        cal.set(Calendar.HOUR, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        val m = Month(
            debit = 0,
            credit = 0,
            monthDate = cal.time,
            currBalance = lastMonth.currBalance,
            prevBalance = lastMonth.currBalance
        )
        return m
    }*/





   /* private suspend fun getLastmonthData(): Month {
        val list =store.collection(MONTH_DATA_COLLECTION)
            .orderBy(MONTHDATE)
            .get()
            .await()

         if(list.isEmpty){
             throw Exception()
         }else{
            return list.documents[list.size()-1].toObject<Month>()!!
         }
    }*/







    /* override suspend fun addExpense(value: ExpenseUiState, onResult: () -> Unit) {
         val op = Operation(
             date = value.date,
             ref = value.ref,
             type = EXPENSE,
             visibleName = value.visibleName,
             value = value.amount.toLong()
         )
         // get month id based on expenseuistate date

         val id= getMonthId(value.date)
         if (id == "") {
             // create Month
             val m =createNewMonthInstance(value.date)
             // add to database
             val ref = addMonth(m)
             // add expense to the new month
             addExpenseToMonth(op,m,ref,onResult)
         } else {
             // if found
             val month = getMonth(id)
             updateMonth(op,month,month.id,onResult)
         }
         // create expense
         // ony for debugging purposes
         Log.d("add enxpense", value.type)
     }*/

    /*override fun addOperation(monthId: String, op: Operation, onResult: (id: String) -> Unit) {
    store
        .collection(MONTH_DATA_COLLECTION)
        .document(monthId)
        .collection(LIST)
        .add(op)
        .addOnSuccessListener { docRef ->
            onResult(docRef.id)
        }.addOnFailureListener {
            throw it
        }
}*/
}