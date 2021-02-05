package com.example.mostpopulararticlestask.presentation.ui.homefragment


import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mostpopulararticlestask.domain.models.Article
import com.example.mostpopulararticlestask.domain.models.MostPobularData
import com.example.mostpopulararticlestask.domain.usecases.GetShareDetailsUseCase
import com.example.mostpopulararticlestask.presentation.core.ErrorHandling
import com.example.mostpopulararticlestask.presentation.getOrAwaitValue
import com.example.mostpopulararticlestask.presentation.rx.SchedulersFacade
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Callable


@RunWith(MockitoJUnitRunner::class)
class ViewModelTest{

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var shareUseCase: GetShareDetailsUseCase
    @Mock
    private lateinit var errorHandling: ErrorHandling
    @Mock
    private lateinit var contextMock: Context

    private lateinit var viewModel: MostPopularViewModel

    private lateinit var article: Article
    private lateinit var mostPobularData: MostPobularData


    @Before
    fun setupTest() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _: Callable<Scheduler?>? -> Schedulers.trampoline() }
         article = Article("","section","source","title","type","abstract")
         mostPobularData = MostPobularData("",0,listOf(article),"")
        val schedulers: SchedulersFacade = SchedulersFacade()
        viewModel = MostPopularViewModel(shareUseCase,schedulers,errorHandling, contextMock )
    }

    @Test
    fun getMostPopular() {

        doReturn(Single.just(mostPobularData)).`when`(shareUseCase).getMostPopular("7")
        viewModel.getMostPopular("7")

        val value = viewModel.items.getOrAwaitValue()
        Assert.assertEquals(1,value.size)
    }


    @Test
    fun openArticle() {
        viewModel.openArticle(article)
        val article = viewModel.openArticleEvent.getOrAwaitValue()

        Assert.assertEquals("type",article!!.type)

    }




}
