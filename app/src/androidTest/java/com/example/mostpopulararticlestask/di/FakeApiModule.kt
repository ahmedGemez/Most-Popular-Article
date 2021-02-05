package com.example.mostpopulararticlestask.di

import com.example.mostpopulararticlestask.data.di.NetworkModule
import com.example.mostpopulararticlestask.data.repos.RemoteRepoImpl
import com.example.mostpopulararticlestask.datasource.FakeRepoImpl
import com.example.mostpopulararticlestask.domain.usecases.RemoteRepo
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
public class FakeApiModule {
    @Provides
    public fun provideRemoteRepo(fakeRepoImpl: FakeRepoImpl): RemoteRepo {
        return fakeRepoImpl;
    }

}