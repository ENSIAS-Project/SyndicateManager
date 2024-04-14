package com.ensias.syndicatemanager.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ensias.syndicatemanager.MONTHDETAILS
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
    private val userService: AccountService,
    private val dataService: DataService,
    ):ViewModel(){
    var monthList = dataService.monthList



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
}