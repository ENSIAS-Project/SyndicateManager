package com.ensias.syndicatemanager.viewmodels

import androidx.lifecycle.ViewModel
import com.ensias.syndicatemanager.MONTHDETAILS
import com.ensias.syndicatemanager.MONTH_DETAILS
import com.ensias.syndicatemanager.exceptions.impl.RegisterPasswordMismatchException
import com.ensias.syndicatemanager.exceptions.impl.UndefinedException
import com.ensias.syndicatemanager.models.Operation
import com.ensias.syndicatemanager.service.DataService
import com.ensias.syndicatemanager.ui.view.SnackbarManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MonthViewModel @Inject constructor(
    private val dataService: DataService,
    private var selectedMonthId : String =""
    ):ViewModel(){
    var monthList = dataService.monthList



    fun onMonthSelect(m: String, open: (String) -> Unit){
        this.selectedMonthId = m
        open(MONTHDETAILS(m))


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