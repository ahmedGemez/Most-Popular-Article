package com.example.mostpopulararticlestask.presentation.di

import android.app.Application
import com.example.mostpopulararticlestask.data.di.ApiModule
import com.example.mostpopulararticlestask.presentation.ui.homefragment.HomeFragment


import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent  {
    fun inject(homeFragment: HomeFragment)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun setContext(app:Application ): Builder
        fun build(): AppComponent
    }

}
