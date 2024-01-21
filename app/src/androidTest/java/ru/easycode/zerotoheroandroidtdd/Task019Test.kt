package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Please also check out the unit test
 * @see ru.easycode.zerotoheroandroidtdd.MainViewModelTest
 */
@RunWith(AndroidJUnit4::class)
class Task019Test {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * For this test please turn on the internet connection
     */
    @Test
    fun test_success() {
        onView(
            allOf(
                withParent(isAssignableFrom(LinearLayout::class.java)),
//                withParent(withId(R.id.rootLayout)),
                isAssignableFrom(ProgressBar::class.java),
//                withId(R.id.progressBar)
            )
        ).check(matches(not(isDisplayed())))

        onView(
            allOf(
                withParent(isAssignableFrom(LinearLayout::class.java)),
//                withParent(withId(R.id.rootLayout)),
                isAssignableFrom(TextView::class.java),
//                withId(R.id.titleTextView),
                withText("Hello World!")
            )
        ).check(matches(not(isDisplayed())))

        onView(
            allOf(
                withParent(isAssignableFrom(LinearLayout::class.java)),
//                withParent(withId(R.id.rootLayout)),
                isAssignableFrom(Button::class.java),
//                withId(R.id.actionButton),
                withText("load")
            )
        ).perform(click())

//        onView(withId(R.id.actionButton)).check(matches(isNotEnabled()))
//        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
//
//        onView(isRoot()).perform(waitTillDisplayed(R.id.titleTextView, 5000))
//        onView(withId(R.id.titleTextView)).check(matches(withText("Hello World From Web!")))
//        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
//        onView(withId(R.id.actionButton)).check(matches(isEnabled()))

        activityScenarioRule.scenario.recreate()
//        onView(withId(R.id.titleTextView)).check(matches(isDisplayed()))
//        onView(withId(R.id.titleTextView)).check(matches(withText("Hello World From Web!")))
//        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
//        onView(withId(R.id.actionButton)).check(matches(isEnabled()))
    }

    /**
     * For this test please turn off the internet connection
     */
    @Test
    fun test_error() {
//        onView(withId(R.id.actionButton)).perform(click())
//        onView(withId(R.id.titleTextView)).check(matches(withText("No internet connection")))
//
//        activityScenarioRule.scenario.recreate()
//        onView(withId(R.id.titleTextView)).check(matches(withText("No internet connection")))
    }
}

@RunWith(AndroidJUnit4::class)
class Task019TestCompose {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    /**
     * For this test please turn on the internet connection
     */
    @Test
    fun test_success() {
        rule.onRoot(useUnmergedTree = false).printToLog("TERT")

        val columnTag = rule.activity.getString(R.string.id_column)
        val progressTag = rule.activity.getString(R.string.id_progress)
        val textTag = rule.activity.getString(R.string.id_text)
        val btnLoadTag = rule.activity.getString(R.string.id_load_button)

        val btnLoadCaption = rule.activity.getString(R.string.btn_load_caption)

        val btnLoad = rule.onNode(
            hasTestTag(btnLoadTag)
                    and
                    hasText(btnLoadCaption)
                    and
                    hasParent(hasTestTag(columnTag))
        )
        val text = rule.onNode(
            hasTestTag(textTag)
                    and
                    hasParent(hasTestTag(columnTag))
        )
        val progress = rule.onNode(
            hasTestTag(progressTag)
                    and
                    hasParent(hasTestTag(columnTag))
        )

        progress.assertDoesNotExist()
        text.assertDoesNotExist()
        btnLoad.assertIsEnabled()

        btnLoad.performClick()

        btnLoad.assertIsNotEnabled()
        progress.assertIsDisplayed()

        // NOTE: Важно, таймаут устанавливать не менее, чем в 1.5 раза больше времени ожидания
        rule.waitUntil(3200) {
            rule.onAllNodesWithTag(textTag).fetchSemanticsNodes().isNotEmpty()
        }

        btnLoad.assertIsEnabled()
        progress.assertDoesNotExist()
        text.assert(hasText("Hello World From Web!"))

        rule.activityRule.scenario.recreate()

        btnLoad.assertIsEnabled()
        progress.assertDoesNotExist()
        text.assertIsDisplayed().assert(hasText("Hello World From Web!"))
    }

    /**
     * For this test please turn off the internet connection
     */
    @Test
    fun test_error() {
        val textTag = rule.activity.getString(R.string.id_text)
        val btnLoadTag = rule.activity.getString(R.string.id_load_button)

        val btnLoad = rule.onNode(
            hasTestTag(btnLoadTag)
        )
        val text = rule.onNode(
            hasTestTag(textTag)
        )

        btnLoad.performClick()

        rule.waitUntil(3200) {
            rule.onAllNodesWithTag(textTag).fetchSemanticsNodes().isNotEmpty()
        }
        text.assert(hasText("No internet connection"))

        rule.activityRule.scenario.recreate()

        text.assert(hasText("No internet connection"))
    }
}