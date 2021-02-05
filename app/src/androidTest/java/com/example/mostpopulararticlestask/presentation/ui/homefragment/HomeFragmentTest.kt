package com.example.mostpopulararticlestask.presentation.ui.homefragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.mostpopulararticlestask.R
import com.example.mostpopulararticlestask.di.DaggerAppComponentTest
import com.example.mostpopulararticlestask.domain.models.Article
import com.example.mostpopulararticlestask.presentation.MostPobularApp
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito


@MediumTest
@RunWith(AndroidJUnit4::class)
class HomeFragmentTest{

    lateinit var instrumentationContext: Context

    @Before
    fun setupTest() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        val myApp = InstrumentationRegistry.getTargetContext().applicationContext as MostPobularApp
        val componentTest =DaggerAppComponentTest.builder().setContext(myApp).build()
        myApp.setAppComponent(componentTest)
        componentTest.inject(this)
    }


    @Test
    fun articleList_DisplayedInUi()   {
        val scenario = launchFragmentInContainer<HomeFragment>(Bundle(), R.style.AppTheme)

        Espresso.onView(ViewMatchers.withId(R.id.progress_container)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.article_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Thread.sleep(2000)
    }

    @Test
    fun clickTask_navigateToDetailFragmentOne()  {

        val scenario = launchFragmentInContainer<HomeFragment>(Bundle(), R.style.AppTheme)

        val navController = Mockito.mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        // WHEN - Click on the first list item
        Espresso.onView(withId(R.id.article_list))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    ViewMatchers.hasDescendant(ViewMatchers.withText("section")), ViewActions.click()
                ))

        val article = Article("", "section", "source", "title", "type", "abstract")

        Mockito.verify(navController).navigate(
            HomeFragmentDirections.actionHomeFragmentToArticleDetailsFragment(article)
        )
        Thread.sleep(2000)
    }





}
