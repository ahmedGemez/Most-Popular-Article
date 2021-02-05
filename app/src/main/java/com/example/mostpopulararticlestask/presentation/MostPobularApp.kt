package com.example.mostpopulararticlestask.presentation

import android.app.Application
import androidx.annotation.VisibleForTesting
import com.example.mostpopulararticlestask.presentation.di.AppComponent
import com.example.mostpopulararticlestask.presentation.di.DaggerAppComponent

class MostPobularApp : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent =  DaggerAppComponent.builder().setContext(this).build()
    }

    public fun getAppComponent():AppComponent{
        return appComponent;
    }

    @VisibleForTesting
    public fun setAppComponent(appComponent: AppComponent){
        this.appComponent = appComponent;
    }

}