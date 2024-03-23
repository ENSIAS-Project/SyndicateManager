package com.ensias.syndicatemanager.di

import androidx.lifecycle.ViewModel
import com.ensias.syndicatemanager.service.AccountService
import com.ensias.syndicatemanager.service.impl.FireBaseAccountService
import com.ensias.syndicatemanager.viewmodels.AuthViewModel
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
    abstract fun provideAccountService(firebase: FireBaseAccountService) : AccountService
}
