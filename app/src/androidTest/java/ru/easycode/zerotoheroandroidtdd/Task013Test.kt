package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyLeftOf
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyRightOf
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Please also check out unit test
 * @see ru.easycode.zerotoheroandroidtdd.CountTest
 */
@RunWith(AndroidJUnit4::class)
class Task013Test {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_increment() {
        onView(
            allOf(
                isAssignableFrom(TextView::class.java),
                //withId(R.id.countTextView),
                withText("0"),
                withParent(isAssignableFrom(LinearLayout::class.java)),
                //withParent(withId(R.id.rootLayout))
            )
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                //withId(R.id.incrementButton),
                withText("+"),
                isAssignableFrom(Button::class.java),
                withParent(isAssignableFrom(LinearLayout::class.java)),
                //withParent(withId(R.id.rootLayout))
            )
        ).check(
            isCompletelyRightOf(
                withId(
                    1//R.id.countTextView
                )
            )
        )

        onView(
            allOf(
                //withId(R.id.decrementButton),
                withText("-"),
                isAssignableFrom(Button::class.java),
                withParent(isAssignableFrom(LinearLayout::class.java)),
                //withParent(withId(R.id.rootLayout))
            )
        ).check(
            isCompletelyLeftOf(
                withId(
                    1//R.id.countTextView
                )
            )
        )

//        onView(withId(R.id.decrementButton)).check(matches(isNotEnabled()))
//        onView(withId(R.id.incrementButton)).perform(click())
//        onView(withId(R.id.countTextView)).check(matches(withText("2")))
//        onView(withId(R.id.decrementButton)).check(matches(isEnabled()))
//
//        onView(withId(R.id.incrementButton)).perform(click())
//        onView(withId(R.id.countTextView)).check(matches(withText("4")))
//        onView(withId(R.id.incrementButton)).check(matches(isNotEnabled()))
//        onView(withId(R.id.decrementButton)).check(matches(isEnabled()))

        activityScenarioRule.scenario.recreate()
//        onView(withId(R.id.countTextView)).check(matches(withText("4")))
//        onView(withId(R.id.incrementButton)).check(matches(isNotEnabled()))
//        onView(withId(R.id.decrementButton)).check(matches(isEnabled()))
//
//        onView(withId(R.id.decrementButton)).perform(click())
//        onView(withId(R.id.countTextView)).check(matches(withText("2")))
//        onView(withId(R.id.incrementButton)).check(matches(isEnabled()))
//        onView(withId(R.id.decrementButton)).check(matches(isEnabled()))
//
//        onView(withId(R.id.decrementButton)).perform(click())
//        onView(withId(R.id.countTextView)).check(matches(withText("0")))
//        onView(withId(R.id.incrementButton)).check(matches(isEnabled()))
//        onView(withId(R.id.decrementButton)).check(matches(isNotEnabled()))

        activityScenarioRule.scenario.recreate()
//        onView(withId(R.id.countTextView)).check(matches(withText("0")))
//        onView(withId(R.id.incrementButton)).check(matches(isEnabled()))
//        onView(withId(R.id.decrementButton)).check(matches(isNotEnabled()))
    }
}

@RunWith(AndroidJUnit4::class)
class Task013TestCompose {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test_increment() {
        rule.onRoot(useUnmergedTree = false).printToLog("TERT")

        val rowTag = rule.activity.getString(R.string.id_row)
        val textTag = rule.activity.getString(R.string.id_text)
        val btnIncTag = rule.activity.getString(R.string.id_increment_button)
        val btnDecTag = rule.activity.getString(R.string.id_decrement_button)

        val btnIncCaption = rule.activity.getString(R.string.btn_increment_caption)
        val btnDecCaption = rule.activity.getString(R.string.btn_decrement_caption)

        val row = rule
            .onNode(
                matcher = hasTestTag(rowTag),
                useUnmergedTree = false
            )

        // Проверка порядка следования элементов внутри Column
        val children = row.onChildren()
        children.assertCountEquals(3)
        val buttonDec = children[0]
        val text = children[1]
        val buttonInc = children[2]

        // init
        buttonDec.assert(
            hasTestTag(btnDecTag)
                    and
                    hasText(btnDecCaption)
        ).assertIsDisplayed().assertIsNotEnabled()

        text.assert(
            hasTestTag(textTag)
                    and
                    hasText("0")
        ).assertIsDisplayed()

        buttonInc.assert(
            hasTestTag(btnIncTag)
                    and
                    hasText(btnIncCaption)
        ).assertIsDisplayed().assertIsEnabled()

        // click 1
        buttonInc.performClick()

        buttonDec.assertIsEnabled()

        text.assert(
            hasText("2")
        ).assertIsDisplayed()

        buttonInc.assertIsEnabled()

        // click 2
        buttonInc.performClick()

        buttonDec.assertIsEnabled()

        text.assert(
            hasText("4")
        ).assertIsDisplayed()

        buttonInc.assertIsNotEnabled()

        rule.activityRule.scenario.recreate()

        buttonDec.assertIsEnabled()

        text.assert(
            hasText("4")
        ).assertIsDisplayed()

        buttonInc.assertIsNotEnabled()
    }
}