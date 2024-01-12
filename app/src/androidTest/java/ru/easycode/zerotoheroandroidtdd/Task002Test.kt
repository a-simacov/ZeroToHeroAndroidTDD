package ru.easycode.zerotoheroandroidtdd

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Task002Test {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_add_id() {
        //onView(withId(R.id.titleTextView)).check(matches(withText("I am an Android Developer!")))
    }
}

@RunWith(AndroidJUnit4::class)
class Task002TestCompose {

    @get:Rule
    val activityRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test_add_id() {
        activityRule.onNodeWithTag("mainTextView").assertIsDisplayed()
    }
}