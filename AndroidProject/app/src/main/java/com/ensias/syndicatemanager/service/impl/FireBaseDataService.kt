package com.ensias.syndicatemanager.service.impl


import android.util.Log
import com.ensias.syndicatemanager.exceptions.DataServiceExceptions
import com.ensias.syndicatemanager.exceptions.impl.NotCurrentMonthException
import com.ensias.syndicatemanager.models.Month
import com.ensias.syndicatemanager.models.Operation
import com.ensias.syndicatemanager.models.SpendType
import com.ensias.syndicatemanager.models.User
import com.ensias.syndicatemanager.service.DataService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.Calendar
import java.util.Date
import java.util.NoSuchElementException
import javax.inject.Inject
import kotlin.jvm.Throws

class FireBaseDataService @Inject constructor(
    private val auth: FirebaseAuth,
    private val store : FirebaseFirestore,
) : DataService {
    private val OPVALUE ="value"
    private val OPTYPE = "type"
    private val OPDATE = "date"
    private val OPREF = "ref"
    private val MONTH_DATA_COLLECTION = "MonthData"
    private val SPEND_TYPES_COLLECTION = "spendTypes"
    private val PREV_BALANCE = "prevBalance"
    private val CURR_BALANCE = "currBalance"
    private val DEBIT = "debit"
    private val CREDIT = "credit"
    private val MONTHDATE = "monthDate"
    private val LIST = "list"
    private val USERS = "Users"
    private val EXPENSE = "s"
    private val LOG = "FirebaseDataService"
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
    override val users : Flow<List<User>>
        get()=auth.currentUser.run{
            store
                .collection(USERS)
                .dataObjects()
        }


    override fun getOperationsFlow(id: String): Flow<List<Operation>> { //tested
        Log.d(LOG,"D getOperationsFlow called with id : ${id}")
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
             var date = doc.getDate("date")
             val ref = doc.getString("ref") ?:continue
             val value = doc.getLong("value")?:continue
             if(! (date is Date)){
                 date = Calendar.getInstance().time
             }
             if(type ==EXPENSE){
                 val spendType = getexpenseType(ref);
                 operation = Operation(id = idop,ref = ref,date = date!!,type = type, value = value, spendtype = spendType)
             }else{
                 val user = getUser(ref);
                 operation = Operation(id = idop,ref = ref,date = date!!,type = type, value = value, user = user)
             }
            operations.add(operation)
         }
         emit(operations)
     }
    }

    @Throws(DataServiceExceptions::class)
    private suspend fun getexpenseType(id:String):SpendType{ //tested
        Log.d(LOG,"D getExpenseType called with id : ${id}")
        var ret = SpendType()
        expensesTypes.first{true}.forEach{
            if (it.id==id){
                ret = it
            }
        }
        if (ret.id == ""){
            throw Exception("spendType not found")
        }else{
            return ret
        }
    }

    @Throws(DataServiceExceptions::class)
    private suspend fun getUser(id:String):User{
        Log.d(LOG,"D getUser called with id : ${id}")
        var ret = User()
        users.first { true }
            .forEach{
            if(it.id==id){
                ret = it
            }
        }
        if(ret.id ==""){
            throw Exception("User not found")
        }else{
            return ret
        }
    }

    @Throws(DataServiceExceptions::class)
    override fun addExpenseType(name: String, onResult: () -> Unit) {
        Log.d(LOG,"D addExpense called with name : ${name}")
        val s = SpendType(name = name)
        store
            .collection(SPEND_TYPES_COLLECTION)
            .add(s)
            .addOnSuccessListener {
                onResult()
            }.addOnFailureListener {
                onFirestoreException(it)
            }
    }

    @Throws(DataServiceExceptions::class)
    override fun updateExpenseType(id: String, newname: String, onResult: () -> Unit) {
        store
            .collection(SPEND_TYPES_COLLECTION)
            .document(id)
            .update("name", newname)
            .addOnSuccessListener { onResult() }
            .addOnFailureListener { onFirestoreException(it) }
    }

    @Throws(DataServiceExceptions::class)
    fun updateMonth(m: Month, onResult: () -> Unit) {
        Log.d(LOG,"D UpdateMonth called month : ${m}")
        val toStore  = hashMapOf(
            CREDIT to m.credit,
            CURR_BALANCE to m.currBalance,
            DEBIT to m.debit,
            MONTHDATE to m.monthDate,
            PREV_BALANCE to m.prevBalance
        )
        store.collection(MONTH_DATA_COLLECTION)
            .document(m.id)
            .set(toStore, SetOptions.merge())
            .addOnSuccessListener { onResult() }
            .addOnFailureListener { onFirestoreException(it) }
    }

    @Throws(DataServiceExceptions::class)
    private suspend fun addMonth(m: Month):Month { // need testing
        Log.d(LOG,"D add Month called with month: ${m}")
        val toStore  = hashMapOf(
            CREDIT to m.credit,
            CURR_BALANCE to m.currBalance,
            DEBIT to m.debit,
            MONTHDATE to m.monthDate,
            PREV_BALANCE to m.prevBalance
        )
        store
            .collection(MONTH_DATA_COLLECTION)
            .add(toStore)
            .addOnSuccessListener { docRef ->
                m.id =docRef.id
            }.addOnFailureListener {
                onFirestoreException(it)
            }.await() // need to wait in order to have
         return m
    }
    fun getMonthDateBasedOnOpDate(date:Date):Date{
        val cal = Calendar.getInstance()
        cal.time = date
        cal.set(Calendar.MILLISECOND,0)
        cal.set(Calendar.SECOND,0)
        cal.set(Calendar.MINUTE,0)
        cal.set(Calendar.AM_PM,Calendar.AM)
        cal.set(Calendar.ZONE_OFFSET,0)
        cal.set(Calendar.DST_OFFSET,0)
        cal.set(Calendar.HOUR,0)
        cal.set(Calendar.DAY_OF_MONTH,1)
        return cal.time
    }
    @Throws(DataServiceExceptions::class)
    override suspend fun addOperation(op: Operation, onResult: () -> Unit) {
        Log.d(LOG,"D addOperation called with an op value : ${op.value}")
        if(checkCurrentMonth(op)){
            //get the month
            val month = getMonthByDateOrCreateNewOne(getMonthDateBasedOnOpDate(op.date))
            //build data to store
            val opToStore  = hashMapOf(
                OPVALUE to op.value,
                OPTYPE to op.type,
                OPDATE to op.date,
                OPREF to op.ref)
            // storew the hashmap as operation on firestore
            store
                .collection(MONTH_DATA_COLLECTION)
                .document(month.id)
                .collection(LIST)
                .add(opToStore).addOnSuccessListener {
                    if(op.type==EXPENSE){
                        month.currBalance -= op.value
                        month.credit += op.value
                    }else{
                        month.currBalance += op.value
                        month.debit += op.value
                    }
                    updateMonth(month,onResult)
                }.addOnFailureListener{ onFirestoreException(it)}
        }else{
            throw NotCurrentMonthException()
        }

    }

    @Throws(DataServiceExceptions::class)
    override suspend fun removeOperation(op: Operation, onResult: () -> Unit) {
        Log.d(LOG,"D remove operation called with date : ${op.date}")
        var calledonce = true
        if(checkCurrentMonth(op)){
            Log.d(LOG,"checkCurrentMonth returned true")
            //get the month
            val month = getMonthByDateOrCreateNewOne(getMonthDateBasedOnOpDate(op.date))
            //build data to store
            val opToStore  = hashMapOf(
                OPVALUE to op.value,
                OPTYPE to op.type,
                OPDATE to op.date,
                OPREF to op.ref)
            // storew the hashmap as operation on firestore
            store
                .collection(MONTH_DATA_COLLECTION)
                .document(month.id)
                .collection(LIST)
                .document(op.id)
                .delete()
                //.add(opToStore)
                .addOnSuccessListener {
                    if(op.type==EXPENSE){
                        month.currBalance += op.value
                        month.credit -= op.value
                    }else{
                        month.currBalance -= op.value
                        month.debit -= op.value
                    }
                    if(calledonce){
                        updateMonth(month,onResult)
                        Log.d("FireBaseDataServioce","update month called")
                        calledonce = false
                    }
                }.addOnFailureListener{ onFirestoreException(it)}
        }else{
            Log.d(LOG,"checkCurrentMonth returned false")
            throw NotCurrentMonthException()
        }
    }
    private fun checkCurrentMonth(op: Operation): Boolean {
        Log.d(LOG,"D CheckCurrentMonth called")
        val opdate = getMonthDateBasedOnOpDate(op.date)
        val thisdate = getMonthDateBasedOnOpDate(Calendar.getInstance().time)
        Log.d(LOG,"D CheckCurrentMonth will return ${opdate == thisdate}")
        return opdate == thisdate
    }

    @Throws(DataServiceExceptions::class)
    private suspend fun getMonthByDateOrCreateNewOne(time: Date): Month {
        Log.d(LOG,"D getMonthByDateOrCreateNewOne called with date : ${time}")
        val v =  store.collection(MONTH_DATA_COLLECTION)
            .whereEqualTo(MONTHDATE,time)
            .get()
            .addOnFailureListener{

            }
            .await()
            .toObjects<Month>()
        if(v.size!=0){
            Log.d(LOG,"D getMonthByDateOrCreateNewOne month found with id: ${v[0].id}")
            return v[0]
        }
        // month not found create one
        Log.d(LOG,"D getMonthByDateOrCreateNewOne will create new one")

        var newMonth : Month
        val date = getMonthDateBasedOnOpDate(time)
        try {
            val p = store.collection(MONTH_DATA_COLLECTION)
                .orderBy(MONTHDATE)
                .get()
                .await()
                val lastMonthBalance = p.last { true }.getLong(CURR_BALANCE)?:0
                newMonth = Month(prevBalance = lastMonthBalance, monthDate = date, currBalance = lastMonthBalance)
        }catch (e:NoSuchElementException){
            newMonth = Month(prevBalance = 0, monthDate = date, currBalance = 0)
        }
        return addMonth(newMonth)
    }

    @Throws(DataServiceExceptions::class)
    fun onFirestoreException(e: java.lang.Exception){
        throw e // todo: read documentation and decide which types of exceptions to implement
    }
}