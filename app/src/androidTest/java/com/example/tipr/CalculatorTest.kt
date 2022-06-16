package com.example.tipr

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_default_tip() {
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("123"))
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.tip_amount)).check(matches(withText("Tip Amount: $25.00")))
        onView(withId(R.id.total_amount)).check(matches(withText("Total: $148.00")))
    }

    @Test
    fun calculate_tip_without_rounding() {
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("234"))
        onView(withId(R.id.tip_round_up)).perform(click())
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.tip_amount)).check(matches(withText("Tip Amount: $46.80")))
        onView(withId(R.id.total_amount)).check(matches(withText("Total: $280.80")))
    }

    @Test
    fun calculate_good_tip() {
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("65.25"))
        onView(withId(R.id.good_tip)).perform(click())
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.tip_amount)).check(matches(withText("Tip Amount: $12.00")))
        onView(withId(R.id.total_amount)).check(matches(withText("Total: $77.25")))
    }

    @Test
    fun calculate_okay_tip() {
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("34"))
        onView(withId(R.id.okay_tip)).perform(click())
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.tip_amount)).check(matches(withText("Tip Amount: $6.00")))
        onView(withId(R.id.total_amount)).check(matches(withText("Total: $40.00")))
    }
}