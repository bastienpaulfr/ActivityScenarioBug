package com.example.activityscenariobug

import android.content.Intent
import android.net.Uri
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import com.example.activityscenariobug.deeplink.DeepLinkSimulator
import com.example.activityscenariobug.utils.LazyActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val mainActivityScenarioRule = LazyActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val mainActivityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun pressingBackGoesBackToMainActivityWithActivityScenario() {
        mainActivityScenarioRule.launch()
        testFlow()
    }

    @Test
    fun pressingBackGoesBackToMainActivityWithActivityTestRule() {
        mainActivityTestRule.launchActivity(null)
        testFlow()
    }

    private fun testFlow() {
        // Main activity is up
        verifyThatMainActivityIsUp()
        onView(withId(R.id.button)).perform(click())
        // Activity A is up
        verifyThatActivityAIsUp()
        onView(withId(R.id.button2)).perform(click())
        // Activity B is up
        verifyThatActivityBIsUp()
        // go home
        pressHome()
        // simulate deeplink
        navigateWithDeeplink()
        //verify activity C
        verifyThatActivityCIsUp()
        goBackFromActivityC()
    }

    private fun goBackFromActivityC() {
        // Press back
        Espresso.pressBack()
        //verify that we are on Activity B
        onView(withId(R.id.button3)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        // Press back
        Espresso.pressBack()
        //verify that we are on Activity A
        onView(withId(R.id.button2)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        // Press back
        Espresso.pressBack()
        //verify that we are on Main Activity
        onView(withId(R.id.button)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    private fun verifyThatActivityAIsUp() {
        onView(withId(R.id.button2)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    private fun verifyThatMainActivityIsUp() {
        onView(withId(R.id.button)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    private fun verifyThatActivityBIsUp() {
        onView(withId(R.id.button3)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    private fun verifyThatActivityCIsUp() {
        onView(withId(R.id.textView2)).check(matches(withText("Hey, you are here")))
    }

    private fun pressHome() {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).pressHome()
    }

    private fun navigateWithDeeplink() {
        ActivityScenario.launch<DeepLinkSimulator>(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("activity-scenario-bug://deeplink")
            )
        )
    }
}
