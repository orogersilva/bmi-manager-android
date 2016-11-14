package com.orogersilva.bmimanager.util;

/**
 * Created by orogersilva on 11/13/2016.
 */

public class Calc {

    // region STATIC METHODS

    /**
     * Calculate BMI.
     * @param weight (kg).
     * @param height (m).
     * @return BMI.
     */
    public static double calcBmi(int weight, double height) {

        if (height == 0) {

            throw new IllegalArgumentException();
        }

        return weight / (height * height);
    }

    // endregion
}
