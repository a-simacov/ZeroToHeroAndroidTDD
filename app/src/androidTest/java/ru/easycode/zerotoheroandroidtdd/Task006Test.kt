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
import androidx.test.espresso.assertion.PositionAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Task006Test {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_change_text_runtime() {
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
        ).check(PositionAssertions.isCompletelyBelow(withId(
            1//R.id.titleTextView
        )))

        //onView(withId(R.id.changeButton)).perform(click())
        //onView(withId(R.id.titleTextView)).check(matches(withText("I am an Android Developer!")))
    }
}

@RunWith(AndroidJUnit4::class)
class Task006TestCompose {

    object Tags {
        const val column = "column"
        const val textTag = "text"
        const val btnTag = "button"
    }

    @get:Rule
    val activityRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test_change_text_runtime() {
        activityRule.onRoot(useUnmergedTree = false).printToLog("TERT")

        //val columnTag = activityRule.activity.getString(R.string.columnTag)

        val column = activityRule
            .onNode(
                matcher = hasTestTag(Tags.column),
                useUnmergedTree = false
            )

        column.printToLog("TERT")

        // Проверка порядка следования элементов внутри Column
        val children = column.onChildren()
        val text = children[0]
        val button = children[1]

        text.assert(
            hasTestTag(Tags.textTag)
                    and
                    hasText("Hello World!")
        )
        button.assert(
            hasTestTag(Tags.btnTag)
                    and
                    hasText("change")
        )

        button.performClick()
        text.assert(hasText("I am an Android Developer!"))
    }
}