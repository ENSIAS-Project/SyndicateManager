package com.ensias.syndicatemanager.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ensias.syndicatemanager.MONTHDETAILS
import com.ensias.syndicatemanager.R
import com.ensias.syndicatemanager.di.Repo
import com.ensias.syndicatemanager.exceptions.impl.NotCurrentMonthException
import com.ensias.syndicatemanager.exceptions.impl.UndefinedException
import com.ensias.syndicatemanager.models.Operation
import com.ensias.syndicatemanager.service.AccountService
import com.ensias.syndicatemanager.service.DataService
import com.ensias.syndicatemanager.ui.view.SnackbarManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MonthViewModel @Inject constructor(
    private val dataService: DataService,
    ):ViewModel(){
    val isADMIN = Repo.user.IS_ADMIN
    var monthList = dataService.monthList
    private var lastDeletedOperationId = ""



    fun onMonthSelect(mId: String,m:Int,y:Int, open: (String) -> Unit){
        open(MONTHDETAILS(mId,m,y))
    }

    fun getOperatioFlow(id:String?): Flow<List<Operation>>{
            return if(id==null){
                SnackbarManager.showMessage(UndefinedException())
                flow{} //empty flow
            }else{
                dataService.getOperationsFlow(id)
            }
        }

    fun deleteOperation(op: Operation): Boolean {
        Log.d("MonthViewModel","delete operation called with op id: ${op.id}")
        var toReturn = false
        if(lastDeletedOperationId!=op.id){
            lastDeletedOperationId = op.id
            toReturn = dataService.checkCurrentMonth(op)
            viewModelScope.launch {
                try{
                    if(isADMIN){
                        dataService.removeOperation(op) { onDeleteSucseeded() }
                    }
                }catch(e:NotCurrentMonthException) {
                    SnackbarManager.showMessage(e.getmessage())

                }catch (e:Exception){
                    SnackbarManager.showMessage(UndefinedException())
                }
            }
        }
        return toReturn
    }

    private fun onDeleteSucseeded() {
            SnackbarManager.showMessage(R.string.OPERATION_SUCESSFUL)
    }
}