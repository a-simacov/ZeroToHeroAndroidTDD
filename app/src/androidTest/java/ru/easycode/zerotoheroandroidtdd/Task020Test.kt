package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.textfield.TextInputEditText
import org.hamcrest.Matchers.allOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Task020Test {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_input() {
        onView(
            allOf(
                withParent(isAssignableFrom(LinearLayout::class.java)),
//                withParent(withId(R.id.rootLayout)),
                isAssignableFrom(TextInputEditText::class.java),
//                withId(R.id.inputEditText)
            )
        ).check(matches(withText("")))

        onView(
            allOf(
                withParent(isAssignableFrom(LinearLayout::class.java)),
//                withParent(withId(R.id.rootLayout)),
                isAssignableFrom(Button::class.java),
//                withId(R.id.actionButton),
                withText("change")
            )
        ).check(matches(isEnabled()))

        onView(
            allOf(
                withParent(isAssignableFrom(LinearLayout::class.java)),
//                withParent(withId(R.id.rootLayout)),
                isAssignableFrom(TextView::class.java),
//                withId(R.id.titleTextView),
//                withText("Hello World!")
            )
        ).check(matches(isDisplayed()))

//        onView(withId(R.id.inputEditText)).perform(typeText("A new text now!"), closeSoftKeyboard())
//        onView(withId(R.id.actionButton)).perform(click())
//        onView(withId(R.id.inputEditText)).check(matches(withText("")))
//        onView(withId(R.id.titleTextView)).check(matches(withText("A new text now!")))

        activityScenarioRule.scenario.recreate()
//        onView(withId(R.id.inputEditText)).check(matches(withText("")))
//        onView(withId(R.id.titleTextView)).check(matches(withText("A new text now!")))
    }
}

@RunWith(AndroidJUnit4::class)
class Task020TestCompose {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test_input() {
        rule.onRoot(useUnmergedTree = false).printToLog("TERT")

        val columnTag = rule.activity.getString(R.string.id_column)
        val editTextTag = rule.activity.getString(R.string.inputEditText)
        val textTag = rule.activity.getString(R.string.titleTextView)
        val btnActionTag = rule.activity.getString(R.string.actionButton)

        val btnActionCaption = rule.activity.getString(R.string.actionButtonCaption)

        val editText = rule.onNode(
            hasTestTag(editTextTag)
                    and
                    hasParent(hasTestTag(columnTag))
        )
        val btnAction = rule.onNode(
            hasTestTag(btnActionTag)
                    and
                    hasText(btnActionCaption)
                    and
                    hasParent(hasTestTag(columnTag))
        )
        val text = rule.onNode(
            hasTestTag(textTag)
                    and
                    hasParent(hasTestTag(columnTag))
        )

        editText.assertTextEquals("")
        btnAction.assertIsEnabled()
        text.assertTextEquals("Hello World!")

        editText.performTextInput("A new text now!")
        closeSoftKeyboard()
        btnAction.performClick()
        editText.assertTextEquals("")
        text.assertTextEquals("A new text now!")

        rule.activityRule.scenario.recreate()

        editText.assertTextEquals("")
        text.assertTextEquals("A new text now!")
    }
}