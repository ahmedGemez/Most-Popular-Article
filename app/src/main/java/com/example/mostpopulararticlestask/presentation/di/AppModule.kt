package com.example.mostpopulararticlestask.presentation.di

import android.app.Application
import android.content.Context
import com.example.mostpopulararticlestask.presentation.rx.SchedulersFacade
import com.example.mostpopulararticlestask.presentation.rx.SchedulersProvider


import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
abstract class AppModule {
    @Binds
    abstract fun bindContext(application: Application): Context


    @Singleton
    @Binds
    abstract fun providerScheduler(schedulersFacade: SchedulersFacade): SchedulersProvider
}