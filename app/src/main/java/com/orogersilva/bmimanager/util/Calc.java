package com.orogersilva.bmimanager.util;

/**
 * Created by orogersilva on 11/13/2016.
 */

public class Calc {

    // region STATIC METHODS

    /**
     * Calculate IMC.
     * @param weight (kg).
     * @param height (m).
     * @return IMC.
     */
    public static double calcImc(int weight, double height) {

        if (height == 0) {

            throw new IllegalArgumentException();
        }

        return weight / (height * height);
    }

    // endregion
}
