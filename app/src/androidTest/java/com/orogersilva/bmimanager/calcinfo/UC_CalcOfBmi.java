package com.orogersilva.bmimanager.calcinfo;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.test.suitebuilder.annotation.LargeTest;

import com.orogersilva.bmimanager.R;
import com.squareup.spoon.Spoon;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.runner.lifecycle.Stage.RESUMED;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class UC_CalcOfBmi {

    // region FIELDS

    @Rule
    public ActivityTestRule<CalcActivity> mActivityTestRule = new ActivityTestRule<>(CalcActivity.class);

    // endregion

    // region TEST METHODS

    @Test
    public void givenEmptyInputFields_whenIFillTheFieldsWithValidData_thenMyBmiAndDiagnosisAreShownOnTheScreen() throws ParseException {

        // ARRANGE

        final String WEIGHT = "94";
        final String METERS_HEIGHT = "1";
        final String CENTIMETERS_HEIGHT = "94";

        final double EXPECTED_BMI = 24.98;

        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());

        final String EXPECTED_BMI_MESSAGE =
                mActivityTestRule.getActivity().getString(R.string.bmi_result_support_message) + " " +
                        nf.format(EXPECTED_BMI) + ".";

        final String EXPECTED_DIAGNOSIS_MESSAGE =
                mActivityTestRule.getActivity().getString(R.string.diagnosis_support_message) + " " +
                mActivityTestRule.getActivity().getString(R.string.bmi_level_4) + "!";

        // ACT

        Spoon.screenshot(mActivityTestRule.getActivity(), "initial_state");

        onView(withId(R.id.weightEditText)).perform(typeText(WEIGHT), pressImeActionButton());
        onView(withId(R.id.meterEditText)).perform(typeText(METERS_HEIGHT), pressImeActionButton());
        onView(withId(R.id.centimeterEditText)).perform(typeText(CENTIMETERS_HEIGHT), closeSoftKeyboard());

        Spoon.screenshot(mActivityTestRule.getActivity(), "after_typing_input_data");

        onView(withId(R.id.calculateBmiButton)).perform(scrollTo(), click());

        // ASSERT

        Spoon.screenshot(getActivityInstance(), "bmi_result");

        onView(withId(R.id.bmiResultMessageTextView)).check(matches(withText(EXPECTED_BMI_MESSAGE)));
        onView(withId(R.id.diagnosisMessageTextView)).check(matches(withText(EXPECTED_DIAGNOSIS_MESSAGE)));
    }

    // endregion

    // region UTILITY METHODS

    /**
     * Get current Activity shown on the screen.
     * @return Current Activity resumed.
     */
    public Activity getActivityInstance() {

        final Activity[] activity = new Activity[1];

        getInstrumentation().runOnMainSync(new Runnable( ) {

            public void run() {

                Activity currentActivity;

                Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED);

                if (resumedActivities.iterator().hasNext()){

                    currentActivity = (Activity) resumedActivities.iterator().next();
                    activity[0] = currentActivity;
                }
            }
        });

        return activity[0];
    }

    // endregion
}
