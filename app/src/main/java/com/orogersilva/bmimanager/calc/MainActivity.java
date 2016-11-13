package com.orogersilva.bmimanager.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.crashlytics.android.Crashlytics;
import com.orogersilva.bmimanager.R;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    // region ACTIVITY LIFECYCLE METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing Crashlytics...
        Fabric.with(this, new Crashlytics());
    }

    // endregion
}
