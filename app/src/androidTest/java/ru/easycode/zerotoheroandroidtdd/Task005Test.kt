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
class Task005Test {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_button_position() {
        onView(
            allOf(
                isAssignableFrom(TextView::class.java),
                //withId(R.id.titleTextView),
                withText("I am an Android Developer!"),
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
        ).check(
            isCompletelyBelow(
                withId(
                    1//R.id.titleTextView
                )
            )
        )
    }
}

@RunWith(AndroidJUnit4::class)
class Task005TestCompose {

    object Tags {
        const val textTag = "mainTextView"
        const val btnTag = "button"
    }

    @get:Rule
    val activityRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test_button_position() {
        activityRule.onRoot(useUnmergedTree = false).printToLog("TERT")

        //val columnTag = activityRule.activity.getString(R.string.columnTag)

        val column = activityRule
            .onNode(
                matcher = hasTestTag("column"),
                useUnmergedTree = false
            )

        // Проверка порядка следования элементов внутри Column
        val children = column.onChildren()
        val text = children[0]
        val button = children[1]

        text.assert(
            hasTestTag(Tags.textTag)
                    and
                    hasText("I am an Android Developer!")
        )
        button.assert(
            hasTestTag(Tags.btnTag)
                    and
                    hasText("I am a button!")
        )
    }
}