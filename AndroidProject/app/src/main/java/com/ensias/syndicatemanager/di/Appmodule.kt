package com.ensias.syndicatemanager.di

import androidx.lifecycle.ViewModel
import com.ensias.syndicatemanager.viewmodels.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class Appmodule {
    @Binds
    abstract fun provideLoginviewModel(vm : LoginViewModel): ViewModel
}
