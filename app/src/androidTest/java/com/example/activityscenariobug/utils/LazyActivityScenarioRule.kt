package com.example.activityscenariobug.utils

import android.app.Activity
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import org.junit.rules.ExternalResource

class LazyActivityScenarioRule<A : Activity>(
    private val activityClass: Class<A>,
) : ExternalResource() {
    private var scenario: ActivityScenario<A>? = null

    /**
     * Launch an activity by its class
     *
     * Automatically close any previous scenario launch
     */
    fun launch(): ActivityScenario<A> {
        scenario?.close() // We close any potential previous scenario to release underneath resources.
        return ActivityScenario.launch(activityClass).also {
            scenario = it
        }
    }

    /**
     * Launch an activity by sending an Intent
     *
     * Automatically close any previous scenario launch
     */
    fun launch(intent: Intent): ActivityScenario<A> {
        scenario?.close() // We close any potential previous scenario to release underneath resources.
        return ActivityScenario.launch<A>(intent).also {
            scenario = it
        }
    }

    override fun after() {
        super.after()
        scenario?.close()
    }
}

