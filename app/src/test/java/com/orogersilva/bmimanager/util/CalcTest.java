package com.orogersilva.bmimanager.util;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by orogersilva on 11/13/2016.
 */
public class CalcTest {

    // region TEST METHODS

    @Test
    public void calcImc_whenWeightIsZero_returnsZero() {

        // ARRANGE

        final int WEIGHT_ZERO = 0;
        final double HEIGHT = 1.94;

        final double EXPECTED_IMC = 0;

        // ACT

        double imc = Calc.calcImc(WEIGHT_ZERO, HEIGHT);

        // ASSERT

        assertEquals(EXPECTED_IMC, imc);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calcImc_whenHeightIsZero_throwsIllegalArgumentException() {

        // ARRANGE

        final int WEIGHT = 94;
        final double HEIGHT_ZERO = 0.0;

        // ACT / ASSERT

        Calc.calcImc(WEIGHT, HEIGHT_ZERO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calcImc_whenWeightAndHeightAreZero_throwsIllegalArgumentException() {

        // ARRANGE

        final int WEIGHT_ZERO = 0;
        final double HEIGHT_ZERO = 0.0;

        // ACT / ASSERT

        Calc.calcImc(WEIGHT_ZERO, HEIGHT_ZERO);
    }

    @Test
    public void calcImc_whenInputsAreValid_calcWasSuccessful() {

        // ARRANGE

        final int WEIGHT = 94;
        final double HEIGHT = 1.94;

        final double EXPECTED_IMC = 24.9;
        final double EPSILON = 0.1;

        // ACT

        double imc = Calc.calcImc(WEIGHT, HEIGHT);

        // ASSERT

        assertEquals(EXPECTED_IMC, imc, EPSILON);
    }

    // endregion
}
