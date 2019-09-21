package com.tiki.hometest


import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import androidx.test.core.app.ApplicationProvider
import com.tiki.hometest.ui.main.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.junit.Assert.*
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config




@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
class MainActivityTest {
    private lateinit var controller: ActivityController<MainActivity>
    private lateinit var activity : MainActivity
    @Before
    fun setup(){
        controller = Robolectric.buildActivity(MainActivity::class.java)
    }
    @Test
    fun validateTextViewTitleContent(){
        createWithIntent()
        val tvTitle : AppCompatTextView = activity.findViewById(R.id.tvTitle)
        assertNotNull("Textview title could not be found", tvTitle)
        assertTrue("Textview title contain incorrect text",
            (ApplicationProvider.getApplicationContext() as Context).getString(R.string.title_keywords) == tvTitle.text
        )
    }
    fun createWithIntent(){
        val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java)
        activity = controller.newIntent(intent)
            .create()
            .start()
            .resume()
            .visible()
            .get()
    }
    @Test
    fun pauseAndResumeActivity(){
        createWithIntent()
        controller.pause().resume()
    }
    @Test
    fun recreateActivity(){
        val bundle = Bundle()
        // Destroy the original activity
        controller
            .saveInstanceState(bundle)
            .pause()
            .stop()
            .destroy()

        // Bring up a new activity
        controller = Robolectric.buildActivity(MainActivity::class.java)
            .create(bundle)
            .start()
            .restoreInstanceState(bundle)
            .resume()
            .visible()
        activity = controller.get()
    }
    @After
    fun tearDown(){
        controller.pause()
            .stop()
            .destroy()
    }
}