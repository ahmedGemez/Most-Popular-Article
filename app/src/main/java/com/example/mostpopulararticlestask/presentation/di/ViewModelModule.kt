package com.example.mostpopulararticlestask.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mostpopulararticlestask.presentation.ui.homefragment.MostPopularViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton


@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Singleton
    @Binds
    @IntoMap
    @ViewModelKey(MostPopularViewModel::class)
    abstract fun bindBaseViewModel(viewModel: MostPopularViewModel): ViewModel



}