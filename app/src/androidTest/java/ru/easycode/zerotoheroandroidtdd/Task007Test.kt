package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
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
class Task007Test {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_keep_text() {
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
                //withId(R.id.changeButton),
                withText("change"),
                isAssignableFrom(Button::class.java),
                withParent(isAssignableFrom(LinearLayout::class.java)),
                //withParent(withId(R.id.rootLayout))
            )
        ).check(isCompletelyBelow(withId(
            1//R.id.titleTextView
        )))

        //onView(withId(R.id.changeButton)).perform(ViewActions.click())
        //onView(withId(R.id.titleTextView)).check(matches(withText("I am an Android Developer!")))

        activityScenarioRule.scenario.recreate()
        //onView(withId(R.id.titleTextView)).check(matches(withText("I am an Android Developer!")))
    }
}

@RunWith(AndroidJUnit4::class)
class Task007TestCompose {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test_keep_text() {
        rule.onRoot(useUnmergedTree = false).printToLog("TERT")

        val columnTag = rule.activity.getString(R.string.id_column)
        val textTag = rule.activity.getString(R.string.id_text)
        val buttonTag = rule.activity.getString(R.string.id_changeButton)

        val column = rule
            .onNode(
                matcher = hasTestTag(columnTag),
                useUnmergedTree = false
            )

        column.printToLog("TERT")

        // Проверка порядка следования элементов внутри Column
        val children = column.onChildren()
        val text = children[0]
        val button = children[1]

        text.assert(
            hasTestTag(textTag)
                    and
                    hasText("Hello World!")
        )
        button.assert(
            hasTestTag(buttonTag)
                    and
                    hasText("change")
        )

        button.performClick()
        text.assert(
            hasText("I am an Android Developer!")
        )

        rule.activityRule.scenario.recreate()

        text.assert(
            hasText("I am an Android Developer!")
        )
    }
}