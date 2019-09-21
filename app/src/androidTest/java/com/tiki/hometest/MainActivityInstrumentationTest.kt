package com.tiki.hometest

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.test.InstrumentationRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.test.runner.intercepting.SingleActivityFactory
import com.tiki.hometest.ui.main.MainActivity
import com.tiki.hometest.ui.main.MainViewModel
import com.tiki.hometest.util.ViewModelUtil
import com.tiki.hometest.vo.Keyword
import com.tiki.hometest.vo.Resource
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify


@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentationTest {
    private var viewModel: MainViewModel =  Mockito.mock(MainViewModel::class.java)
    private val keywordLiveData = MutableLiveData<Resource<List<Keyword>>>()

    private val injectedFactory = object : SingleActivityFactory<MainActivity>(MainActivity::class.java) {
        override fun create(intent: Intent): MainActivity {
            val activity = MainActivity()
            //val testApp = ApplicationProvider.getApplicationContext() as TestApp
            activity.viewModelFactory = ViewModelUtil.createFor(viewModel)
            return activity
        }
    }
    @Suppress("MemberVisibilityCanBePrivate")
    @get:Rule
    val activityRule = ActivityTestRule(injectedFactory,false, false)

    @Before
    fun init(){
        `when`(viewModel.keyword).thenReturn(keywordLiveData)
        val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java)
        activityRule.launchActivity(intent)
    }
    @Test
    fun loading(){
        keywordLiveData.postValue(Resource.loading(null))
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.retry)).check(matches(not(isDisplayed())))
    }
    @Test
    fun error(){
        keywordLiveData.postValue(Resource.error("foo bar",null))
        onView(withId(R.id.error_msg)).check(matches(isDisplayed()))
        onView(withId(R.id.error_msg)).check(matches(withText("foo bar")))

        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
        onView(withId(R.id.retry)).check(matches(isDisplayed()))

        onView(withId(R.id.retry)).perform(click())
        verify(viewModel).retry()
    }
    @Test
    fun success(){
        val data = mutableListOf<Keyword>()
        keywordLiveData.postValue(Resource.success(data))

        //onView(withId(R.id.tvTitle)).check(matches(not(isDisplayed())))

        onView(withId(R.id.error_msg)).check(matches(not(isDisplayed())))
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
        onView(withId(R.id.retry)).check(matches(not(isDisplayed())))
    }


}