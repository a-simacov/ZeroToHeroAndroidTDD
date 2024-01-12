package ru.easycode.zerotoheroandroidtdd

import android.widget.LinearLayout
import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers.allOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Task003Test {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_change_parent() {
        onView(
            allOf(
                //withId(R.id.titleTextView),
                withText("I am an Android Developer!"),
                withParent(isAssignableFrom(LinearLayout::class.java))
            )
        ).check(matches(isDisplayed()))
    }
}

@RunWith(AndroidJUnit4::class)
@LargeTest
class Task003TestCompose {

    @get:Rule
    val activityRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test_change_parent() {
        activityRule.onRoot(useUnmergedTree = true).printToLog("TERT")

        val parent = activityRule.onNodeWithTag("column")
        parent.assertExists()
        val child = activityRule.onNodeWithTag("mainTextView")
        child.assertIsDisplayed()

        // 1 способ
        activityRule
            .onNode(
                matcher = hasTestTag("mainTextView")
                        and
                        hasText("I am an Android Developer!")
                        and
                        hasParent(
                            hasTestTag("column")
                        ),
                useUnmergedTree = true
            ).assertIsDisplayed()

        // 2 способ
        activityRule
            .onNode(
                matcher = hasTestTag("column")
                        and
                        hasAnyChild(
                            hasTestTag("mainTextView")
                                    and
                                    hasText("I am an Android Developer!")
                        ),
                useUnmergedTree = true
            )
            .assertExists()
            .assertIsDisplayed()

        // 3 способ
        activityRule
            .onNode(matcher = hasTestTag("column"))
            .assertExists()
            .onChildren()
            .filter(
                hasTestTag("mainTextView")
            )
            .assertAll(
                hasText("I am an Android Developer!")
            )
    }
}