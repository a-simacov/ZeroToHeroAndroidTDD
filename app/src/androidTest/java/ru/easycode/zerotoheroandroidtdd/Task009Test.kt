package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyBelow
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

@RunWith(AndroidJUnit4::class)
class Task009Test {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_remove_textview() {
        onView(
            allOf(
                isAssignableFrom(TextView::class.java),
                //withId(R.id.titleTextView),
                withText("Hello World!"),
                withParent(isAssignableFrom(LinearLayout::class.java)),
                //withParent(withId(R.id.rootLayout))
            )
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                //withId(R.id.removeButton),
                withText("remove"),
                isAssignableFrom(Button::class.java),
                withParent(isAssignableFrom(LinearLayout::class.java)),
                //withParent(withId(R.id.rootLayout))
            )
        ).check(
            isCompletelyBelow(
                withId(
                    1//R.id.titleTextView
                )
            )
        )

//        onView(withId(R.id.removeButton)).perform(click())
//        onView(withId(R.id.titleTextView)).check(doesNotExist())

        activityScenarioRule.scenario.recreate()
        //onView(withId(R.id.titleTextView)).check(doesNotExist())
    }
}

@RunWith(AndroidJUnit4::class)
class Task009TestCompose {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun test_remove_textview() {
        rule.onRoot(useUnmergedTree = false).printToLog("TERT")

        val columnTag = rule.activity.getString(R.string.id_column)
        val textTag = rule.activity.getString(R.string.id_text)
        val btnTag = rule.activity.getString(R.string.id_remove_button)

        val textValue = rule.activity.getString(R.string.hello_world)
        val btnCaption = rule.activity.getString(R.string.btn_remove_caption)

        val column = rule
            .onNode(
                matcher = hasTestTag(columnTag),
                useUnmergedTree = false
            )

        // Проверка порядка следования элементов внутри Column
        val children = column.onChildren()
        val text = children[0]
        val button = children[1]

        text.assert(
            hasTestTag(textTag)
                    and
                    hasText(textValue)
        ).assertIsDisplayed()

        button.assert(
            hasTestTag(btnTag)
                    and
                    hasText(btnCaption)
        ).assertIsDisplayed()

        button.performClick()

        // 1 способ
        rule.waitUntilDoesNotExist(
            hasTestTag(textTag)
        )

        // 2 способ
        rule.waitUntil {
            rule.onAllNodesWithTag(textTag)
                .fetchSemanticsNodes()
                .isEmpty()
        }

        rule.activityRule.scenario.recreate()

        rule.waitUntilDoesNotExist(
            hasTestTag(textTag)
        )
    }
}