package com.example.mostpopulararticlestask.presentation.ui.ArticleDetails

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.mostpopulararticlestask.R
import com.example.mostpopulararticlestask.domain.models.Article
import com.example.mostpopulararticlestask.domain.models.MostPobularData
import org.hamcrest.Matchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class ArticleDetailsFragmentTest{

    private lateinit var article: Article
    private lateinit var mostPobularData: MostPobularData

    @Before
    fun setupTest() {
        article = Article("","section","source","title","type","abstract")
        mostPobularData = MostPobularData("",0,listOf(article),"")
    }

    @Test
    fun articleDetailsFragmentTest_DisplayedInUi()  {

        // WHEN - Details fragment launched to display task
        val bundle = ArticleDetailsFragmentArgs(article).toBundle()
        launchFragmentInContainer<ArticleDetailsFragment>(bundle,R.style.AppTheme)

        Espresso.onView(withId(R.id.title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.details)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.title)).check(ViewAssertions.matches(ViewMatchers.withText("title")))
        Espresso.onView(withId(R.id.details)).check(ViewAssertions.matches(ViewMatchers.withText("abstract")))

        Thread.sleep(3000)
    }

}