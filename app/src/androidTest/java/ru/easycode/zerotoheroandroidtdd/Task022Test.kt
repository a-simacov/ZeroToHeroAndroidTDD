package ru.easycode.zerotoheroandroidtdd

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Task022Test {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_list() {
//        onView(
//            allOf(
//                withParent(isAssignableFrom(LinearLayout::class.java)),
//                withParent(withId(R.id.topLayout)),
//                isAssignableFrom(TextInputEditText::class.java),
//                withId(R.id.inputEditText)
//            )
//        ).check(matches(withText("")))

//        onView(
//            allOf(
//                withParent(isAssignableFrom(LinearLayout::class.java)),
//                withParent(withId(R.id.topLayout)),
//                isAssignableFrom(Button::class.java),
//                withId(R.id.actionButton),
//                withText("create")
//            )
//        ).check(isCompletelyRightOf(withId(R.id.inputEditText)))

//        onView(withId(R.id.inputEditText)).perform(typeText("first text"))
//        onView(withId(R.id.actionButton)).perform(click())
//        onView(withId(R.id.inputEditText)).check(matches(withText("")))

//        onView(
//            allOf(
//                withText("first text"),
//                withParent(isAssignableFrom(LinearLayout::class.java)),
//                withParent(withId(R.id.contentLayout)),
//                isAssignableFrom(TextView::class.java),
//            )
//        ).check(isCompletelyBelow(withId(R.id.inputEditText)))

//        onView(withId(R.id.inputEditText)).perform(typeText("second text"))
//        onView(withId(R.id.actionButton)).perform(click())
//        onView(withId(R.id.inputEditText)).check(matches(withText("")))

//        onView(
//            allOf(
//                withText("first text"),
//                withParent(isAssignableFrom(LinearLayout::class.java)),
//                withParent(withId(R.id.contentLayout)),
//                isAssignableFrom(TextView::class.java),
//            )
//        ).check(isCompletelyBelow(withId(R.id.inputEditText)))

//        onView(
//            allOf(
//                withText("second text"),
//                withParent(isAssignableFrom(LinearLayout::class.java)),
//                withParent(withId(R.id.contentLayout)),
//                isAssignableFrom(TextView::class.java),
//            )
//        ).check(isCompletelyBelow(withText("first text")))

//        onView(withId(R.id.inputEditText)).perform(typeText("third text"))
//        onView(withId(R.id.actionButton)).perform(click())
//        onView(withId(R.id.inputEditText)).check(matches(withText("")))

//        onView(
//            allOf(
//                withText("first text"),
//                withParent(isAssignableFrom(LinearLayout::class.java)),
//                withParent(withId(R.id.contentLayout)),
//                isAssignableFrom(TextView::class.java),
//            )
//        ).check(isCompletelyBelow(withId(R.id.inputEditText)))

//        onView(
//            allOf(
//                withText("second text"),
//                withParent(isAssignableFrom(LinearLayout::class.java)),
//                withParent(withId(R.id.contentLayout)),
//                isAssignableFrom(TextView::class.java),
//            )
//        ).check(isCompletelyBelow(withText("first text")))

//        onView(
//            allOf(
//                withText("third text"),
//                withParent(isAssignableFrom(LinearLayout::class.java)),
//                withParent(withId(R.id.contentLayout)),
//                isAssignableFrom(TextView::class.java),
//            )
//        ).check(isCompletelyBelow(withText("second text")))


//        activityScenarioRule.scenario.recreate()

//        onView(
//            allOf(
//                withText("first text"),
//                withParent(isAssignableFrom(LinearLayout::class.java)),
//                withParent(withId(R.id.contentLayout)),
//                isAssignableFrom(TextView::class.java),
//            )
//        ).check(isCompletelyBelow(withId(R.id.inputEditText)))

//        onView(
//            allOf(
//                withText("second text"),
//                withParent(isAssignableFrom(LinearLayout::class.java)),
//                withParent(withId(R.id.contentLayout)),
//                isAssignableFrom(TextView::class.java),
//            )
//        ).check(isCompletelyBelow(withText("first text")))

//        onView(
//            allOf(
//                withText("third text"),
//                withParent(isAssignableFrom(LinearLayout::class.java)),
//                withParent(withId(R.id.contentLayout)),
//                isAssignableFrom(TextView::class.java),
//            )
//        ).check(isCompletelyBelow(withText("second text")))
    }
}

@RunWith(AndroidJUnit4::class)
class Task022TestCompose {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test_list() {
        rule.onRoot(useUnmergedTree = false).printToLog("TERT")

        val topLayoutTag = rule.activity.getString(R.string.topLayout)
        val editTextTag = rule.activity.getString(R.string.inputEditText)
        val btnActionTag = rule.activity.getString(R.string.actionButton)
        val contentLayoutTag = rule.activity.getString(R.string.contentLayout)

        val btnActionCaption = rule.activity.getString(R.string.actionButtonCaption)

        val editText = rule.onNode(
            hasTestTag(editTextTag)
                    and
                    hasParent(hasTestTag(topLayoutTag))
        )
        val btnAction = rule.onNode(
            hasTestTag(btnActionTag)
                    and
                    hasText(btnActionCaption)
                    and
                    hasParent(hasTestTag(topLayoutTag))
        )

        editText.performTextInput("first text")
        btnAction.performClick()
        editText.assertTextEquals("")

        val content = rule.onNode(hasTestTag(contentLayoutTag))

        var children = content.onChildren()
        children.assertCountEquals(1)

        children[0].assertTextEquals("first text")

        editText.performTextInput("second text")
        btnAction.performClick()
        editText.assertTextEquals("")

        children = content.onChildren()

        children.assertCountEquals(2)
        children[0].assertTextEquals("first text")
        children[1].assertTextEquals("second text")

        editText.performTextInput("third text")
        btnAction.performClick()
        editText.assertTextEquals("")

        children = content.onChildren()

        children.assertCountEquals(3)
        children[0].assertTextEquals("first text")
        children[1].assertTextEquals("second text")
        children[2].assertTextEquals("third text")

        rule.activityRule.scenario.recreate()

        children = content.onChildren()

        children.assertCountEquals(3)
        children[0].assertTextEquals("first text")
        children[1].assertTextEquals("second text")
        children[2].assertTextEquals("third text")
    }
}