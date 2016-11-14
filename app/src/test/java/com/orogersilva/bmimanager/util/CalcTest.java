package com.orogersilva.bmimanager.util;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by orogersilva on 11/13/2016.
 */
public class CalcTest {

    // region TEST METHODS

    @Test
    public void calcBmi_whenWeightIsZero_returnsZero() {

        // ARRANGE

        final int WEIGHT_ZERO = 0;
        final double HEIGHT = 1.94;

        final double EXPECTED_BMI = 0;

        // ACT

        double bmi = Calc.calcBmi(WEIGHT_ZERO, HEIGHT);

        // ASSERT

        assertEquals(EXPECTED_BMI, bmi);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calcBmi_whenHeightIsZero_throwsIllegalArgumentException() {

        // ARRANGE

        final int WEIGHT = 94;
        final double HEIGHT_ZERO = 0.0;

        // ACT / ASSERT

        Calc.calcBmi(WEIGHT, HEIGHT_ZERO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calcBmi_whenWeightAndHeightAreZero_throwsIllegalArgumentException() {

        // ARRANGE

        final int WEIGHT_ZERO = 0;
        final double HEIGHT_ZERO = 0.0;

        // ACT / ASSERT

        Calc.calcBmi(WEIGHT_ZERO, HEIGHT_ZERO);
    }

    @Test
    public void calcBmi_whenInputsAreValid_calcWasSuccessful() {

        // ARRANGE

        final int WEIGHT = 94;
        final double HEIGHT = 1.94;

        final double EXPECTED_BMI = 24.9;
        final double EPSILON = 0.1;

        // ACT

        double bmi = Calc.calcBmi(WEIGHT, HEIGHT);

        // ASSERT

        assertEquals(EXPECTED_BMI, bmi, EPSILON);
    }

    // endregion
}
