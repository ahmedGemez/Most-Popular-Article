package com.example.mostpopulararticlestask.di

import android.app.Application
import com.example.mostpopulararticlestask.presentation.di.AppComponent
import com.example.mostpopulararticlestask.presentation.di.AppModule
import com.example.mostpopulararticlestask.presentation.di.ViewModelModule
import com.example.mostpopulararticlestask.presentation.ui.homefragment.HomeFragmentTest
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FakeApiModule::class,
        AppModule::class,
        ViewModelModule::class
    ]
)
interface AppComponentTest : AppComponent {
    fun inject(homeFragmentTest: HomeFragmentTest)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun setContext(app: Application): Builder
        fun build(): AppComponentTest
    }

}