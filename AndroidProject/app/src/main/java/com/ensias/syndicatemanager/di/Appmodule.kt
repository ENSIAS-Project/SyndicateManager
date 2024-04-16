package com.ensias.syndicatemanager.di

import androidx.lifecycle.ViewModel
import com.ensias.syndicatemanager.service.AccountService
import com.ensias.syndicatemanager.service.DataService
import com.ensias.syndicatemanager.service.impl.FireBaseAccountService
import com.ensias.syndicatemanager.service.impl.FireBaseDataService
import com.ensias.syndicatemanager.viewmodels.AuthViewModel
import com.ensias.syndicatemanager.viewmodels.MainViewModel
import com.ensias.syndicatemanager.viewmodels.MonthViewModel
import com.ensias.syndicatemanager.viewmodels.OperationViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class Appmodule {
    @Binds
    abstract fun provideAuthviewModel(vm : AuthViewModel): ViewModel
    @Binds
    abstract fun provideMainviewModel(vm:MainViewModel):ViewModel
    @Binds
    abstract fun provideMonthViewModel(vm:MonthViewModel):ViewModel
    @Binds
    abstract fun andThisIsAnEasterEggHahaaaaaaaaaa(vm:OperationViewModel):ViewModel

    @Binds
    abstract fun provideAccountService(firebase: FireBaseAccountService) : AccountService

    @Binds
    abstract fun provideDataService(firebase : FireBaseDataService): DataService
}
