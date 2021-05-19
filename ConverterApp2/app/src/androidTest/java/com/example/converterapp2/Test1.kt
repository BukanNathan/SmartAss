package com.example.converterapp2

import android.content.Intent
import android.provider.MediaStore
import android.system.Os.close
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingPolicies
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.google.android.material.internal.ContextUtils.getActivity
import org.hamcrest.Matchers.*
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class Test1 {
    @Rule @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testMainActivityDisplayed() {
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }
    @Test
    fun testButtonDisplayed() {
        onView(withId(R.id.chooseFile)).check(matches(isDisplayed()))
        onView(withId(R.id.fileList)).check(matches(isDisplayed()))
        onView(withId(R.id.resizePic)).check(matches(isDisplayed()))
        onView(withId(R.id.convertion)).check(matches(isDisplayed()))
        onView(withId(R.id.link_convertion)).check(matches(isDisplayed()))
    }

    @Before
    fun setup() {
        Intents.init()
    }
    @Test
    fun zlaunchCamera(){
        var expectedIntent : org.hamcrest.Matcher<Intent>? = hasAction(MediaStore.ACTION_IMAGE_CAPTURE)
        onView(withId(R.id.resizePic)).perform(click())
        onView(withId(R.id.takePic)).perform(click())
        intended(expectedIntent)
    }
    @Test
    fun testResize() {
        onView(withId(R.id.resizePic)).perform(click())
        onView(withId(R.id.editTextNumber)).perform(typeText("120"))
        onView(withId(R.id.editTextNumber2)).perform(typeText("100"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.textView6)).check(matches(withText("120")))
        onView(withId(R.id.textView7)).check(matches(withText("100")))
    }
    @Test
    fun testSpinnerType() {
        onView(withId(R.id.convertion)).perform(click())
        onView(withId(R.id.spinnerType)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("Audio"))).perform(click())
        onView(withId(R.id.spinnerType)).check(matches(withSpinnerText("Audio")))
    }
    @Test
    fun testConvertionTitle() {
        onView(withId(R.id.convertion)).perform(click())
        onView(withId(R.id.spinnerType)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("Video"))).perform(click())
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.convertionType)).check(matches(withText(containsString("Video"))))
    }
    @Test
    fun testShowFileType() {
        onView(withId(R.id.chooseFile)).perform(click())
        onView(withId(R.id.radioDoc)).perform(click())
        onView(withId(R.id.editText1)).perform(typeText("docx"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.textView1)).check(matches(withText("File Type = Document, Extension = .docx")))
    }
    @Test
    fun testProgressBarDisplayed() {
        onView(withId(R.id.convertion)).perform(click())
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
    }
    @Test
    fun testConvertion() {
        onView(withId(R.id.convertion)).perform(click())
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.convert2)).perform(click())
        onView(withText(".pdf")).perform(click())
        onView(withId(R.id.button2)).perform(click())
        IdlingPolicies.setIdlingResourceTimeout(5, TimeUnit.SECONDS)
        onView(withId(R.id.result)).check(matches(withText("Convert .doc to .pdf")))
    }
    @After
    fun tearDown(){
        Intents.release()
    }
}