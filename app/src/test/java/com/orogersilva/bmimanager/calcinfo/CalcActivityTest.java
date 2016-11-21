package com.orogersilva.bmimanager.calcinfo;

import android.os.Build;
import android.widget.Button;
import android.widget.EditText;

import com.orogersilva.bmimanager.BuildConfig;
import com.orogersilva.bmimanager.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.junit.Assert.*;

/**
 * Created by orogersilva on 11/21/2016.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class CalcActivityTest {

    // region FIELDS

    private CalcActivity mCalcActivity;

    private EditText mWeightEditText;
    private EditText mMeterEditText;
    private EditText mCentimeterEditText;
    private Button mCalculateBmiButton;

    // endregion

    // region SETUP METHODS

    @Before
    public void setUp() {

        mCalcActivity = Robolectric.setupActivity(CalcActivity.class);

        mWeightEditText = (EditText) mCalcActivity.findViewById(R.id.weightEditText);
        mMeterEditText = (EditText) mCalcActivity.findViewById(R.id.meterEditText);
        mCentimeterEditText = (EditText) mCalcActivity.findViewById(R.id.centimeterEditText);
        mCalculateBmiButton = (Button) mCalcActivity.findViewById(R.id.calculateBmiButton);
    }

    // endregion

    // region TEST METHODS

    @Test
    public void onCreateActivity_shouldStartItself() {

        // ASSERT

        assertNotNull(mCalcActivity);
        assertEquals(mCalcActivity.getTitle(), mCalcActivity.getString(R.string.calc_info));
    }

    @Test
    public void fillingInputsWithValidData_shouldStartResultsActivity() {

        // ARRANGE

        final String WEIGHT = "94";
        final String METERS_HEIGHT = "1";
        final String CENTIMETERS_HEIGHT = "94";

        // ACT

        mWeightEditText.setText(WEIGHT);
        mMeterEditText.setText(METERS_HEIGHT);
        mCentimeterEditText.setText(CENTIMETERS_HEIGHT);

        mCalculateBmiButton.performClick();

        // ASSERT

        ShadowActivity shadowCalcActivity = Shadows.shadowOf(mCalcActivity);

        assertThat("Next activity has started.", shadowCalcActivity.getNextStartedActivity(), is(notNullValue()));
    }

    // endregion

    // region TEARDOWN METHODS

    @After
    public void teardown() {

        mWeightEditText = mMeterEditText = mCentimeterEditText = null;
        mCalculateBmiButton = null;

        mCalcActivity = null;
    }

    // endregion
}
