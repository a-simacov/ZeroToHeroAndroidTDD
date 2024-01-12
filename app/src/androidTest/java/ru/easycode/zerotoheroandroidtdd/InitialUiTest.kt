package ru.easycode.zerotoheroandroidtdd

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InitialUiTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_hello_world() {
        onView(withText("Hello World!")).check(matches(isDisplayed()))
    }
}

@RunWith(AndroidJUnit4::class)
class InitialUiTest2 {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_hello_world() {
        composeTestRule.setContent {
            Text(text = "Hello World!")
        }

        composeTestRule.onNodeWithText("Hello World!").assertIsDisplayed()

    }
}

@RunWith(AndroidJUnit4::class)
@LargeTest
class InitialUiTestCompose {

    @get:Rule
    val activityRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test_hello_world() {
        activityRule.onNodeWithText("Hello World!").assertIsDisplayed()
    }
}