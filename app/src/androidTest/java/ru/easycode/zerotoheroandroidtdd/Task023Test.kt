package ru.easycode.zerotoheroandroidtdd

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Task023Test {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_recycler() {
//        onView(
//            allOf(
//                withParent(isAssignableFrom(LinearLayout::class.java)),
//                withParent(withId(R.id.topLayout)),
//                isAssignableFrom(TextInputEditText::class.java),
//                withId(R.id.inputEditText)
//            )
//        ).check(matches(withText("")))
//
//        onView(
//            allOf(
//                withParent(isAssignableFrom(LinearLayout::class.java)),
//                withParent(withId(R.id.topLayout)),
//                isAssignableFrom(Button::class.java),
//                withId(R.id.actionButton),
//                withText("create")
//            )
//        ).check(isCompletelyRightOf(withId(R.id.inputEditText)))
//
//        onView(
//            allOf(
//                withParent(withId(R.id.rootLayout)),
//                withParent(isAssignableFrom(LinearLayout::class.java)),
//                isAssignableFrom(RecyclerView::class.java),
//                withId(R.id.recyclerView)
//            )
//        ).check(isCompletelyBelow(withId(R.id.inputEditText)))
//
//        onView(withId(R.id.inputEditText)).perform(typeText("first text"))
//        onView(withId(R.id.actionButton)).perform(click())
//        onView(withId(R.id.inputEditText)).check(matches(withText("")))
//
//        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(0, R.id.elementTextView))
//            .check(matches(withText("first text")))
//
//        onView(withId(R.id.inputEditText)).perform(typeText("second text"))
//        onView(withId(R.id.actionButton)).perform(click())
//        onView(withId(R.id.inputEditText)).check(matches(withText("")))
//
//        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(0, R.id.elementTextView))
//            .check(matches(withText("first text")))
//
//        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(1, R.id.elementTextView))
//            .check(matches(withText("second text")))
//
//
//        for (i in 0..10) {
//            onView(withId(R.id.inputEditText)).perform(typeText("text number $i"))
//            onView(withId(R.id.actionButton)).perform(click())
//            onView(withId(R.id.inputEditText)).check(matches(withText("")))
//
//            onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(i + 2, R.id.elementTextView))
//                .check(matches(withText("text number $i")))
//        }
//
//        activityScenarioRule.scenario.recreate()
//
//        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(0, R.id.elementTextView))
//            .check(matches(withText("first text")))
//
//        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(1, R.id.elementTextView))
//            .check(matches(withText("second text")))
//
//        for (i in 0..10) {
//            onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(i + 2, R.id.elementTextView))
//                .check(matches(withText("text number $i")))
//        }
    }
}

@RunWith(AndroidJUnit4::class)
class Task023TestCompose {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test_recycler() {
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

        content.performScrollToIndex(0)
            .assert(
                hasAnyDescendant(hasText("first text"))
            )

        editText.performTextInput("second text")
        btnAction.performClick()
        editText.assertTextEquals("")

        content.performScrollToIndex(0)
            .assert(
                hasAnyDescendant(hasText("first text"))
            )

        content.performScrollToIndex(1)
            .assert(
                hasAnyDescendant(hasText("second text"))
            )

        for (i in 0..10) {
            editText.performTextInput("text number $i")
            btnAction.performClick()
            editText.assertTextEquals("")

            content.performScrollToIndex(i + 2)
                .assert(
                    hasAnyDescendant(hasText("text number $i"))
                )
        }

        rule.activityRule.scenario.recreate()

        content.performScrollToIndex(0)
            .assert(
                hasAnyDescendant(hasText("first text"))
            )

        content.performScrollToIndex(1)
            .assert(
                hasAnyDescendant(hasText("second text"))
            )

        for (i in 0..10) {
            content.performScrollToIndex(i + 2)
                .assert(
                    hasAnyDescendant(hasText("text number $i"))
                )
        }
    }
}