package com.example.mostpopulararticlestask.domain.usecases

import com.example.mostpopulararticlestask.data.repos.RemoteRepoImpl
import com.example.mostpopulararticlestask.domain.models.Article
import com.example.mostpopulararticlestask.domain.models.MostPobularData
import com.example.mostpopulararticlestask.presentation.getOrAwaitValue
import com.example.mostpopulararticlestask.presentation.rx.SchedulersFacade
import com.example.mostpopulararticlestask.presentation.ui.homefragment.MostPopularViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Callable

@RunWith(MockitoJUnitRunner::class)
class GetShareDetailsUseCaseTest{
    @Mock
    private lateinit var RemoteRepoImpl: RemoteRepoImpl
    private lateinit var article: Article
    private lateinit var mostPobularData: MostPobularData
    @Before
    fun setupTest() {
        article = Article("","section","source","title","type","abstract")
        mostPobularData = MostPobularData("",0,listOf(article),"")
    }

    @Test
    fun getMostPopular() {
        Mockito.doReturn(Single.just(mostPobularData)).`when`(RemoteRepoImpl).getMostPopular("7")
       val result =  RemoteRepoImpl.getMostPopular("7").blockingGet()

        Assert.assertEquals(0,result.numResults)
    }

}