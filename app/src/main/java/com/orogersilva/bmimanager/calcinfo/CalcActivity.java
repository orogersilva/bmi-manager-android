package com.orogersilva.bmimanager.calcinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;

import com.crashlytics.android.Crashlytics;
import com.orogersilva.bmimanager.R;
import com.orogersilva.bmimanager.resultsinfo.ResultsActivity;
import com.orogersilva.bmimanager.util.Calc;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;

public class CalcActivity extends AppCompatActivity {

    // region FIELDS

    private final String TAG = "CalcActivity";

    @BindView(R.id.weightEditText)
    EditText weightEditText;
    @BindView(R.id.meterEditText)
    EditText meterEditText;
    @BindView(R.id.centimeterEditText)
    EditText centimeterEditText;

    public static final String EXTRA_BMI = "extra_bmi";
    public static final String EXTRA_DIAGNOSIS = "extra_diagnosis";

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        // Initializing Crashlytics...
        Fabric.with(this, new Crashlytics());
    }

    @Override
    public void onStart() {

        super.onStart();

        if (weightEditText.requestFocus()) {

            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public void onStop() {

        super.onStop();

        weightEditText.setText("");
        meterEditText.setText("");
        centimeterEditText.setText("");
    }

    // endregion

    // region EVENT HANDLERS METHODS

    @OnClick(R.id.calculateBmiButton)
    public void calculateBmi() {

        // TODO: 11/14/2016 Rewrite this code snippet.

        int metersHeight = Integer.parseInt(meterEditText.getText().toString());
        int centimetersHeight = Integer.parseInt(centimeterEditText.getText().toString());

        int weight = Integer.parseInt(weightEditText.getText().toString());
        double height = metersHeight + ((double) centimetersHeight / 100);

        double bmi = Calc.calcBmi(weight, height);

        String diagnosis = null;

        if (bmi < 16) diagnosis = getString(R.string.bmi_level_1);
        else if (bmi >= 16 && bmi < 17) diagnosis = getString(R.string.bmi_level_2);
        else if (bmi >= 17 && bmi < 18.5) diagnosis = getString(R.string.bmi_level_3);
        else if (bmi >= 18.5 && bmi < 25) diagnosis = getString(R.string.bmi_level_4);
        else if (bmi >= 25 && bmi < 30) diagnosis = getString(R.string.bmi_level_5);
        else if (bmi >= 30 && bmi < 35) diagnosis = getString(R.string.bmi_level_6);
        else if (bmi >= 35 && bmi < 40) diagnosis = getString(R.string.bmi_level_7);
        else if (bmi >= 40) diagnosis = getString(R.string.bmi_level_8);

        Intent intent = new Intent(this, ResultsActivity.class);

        intent.putExtra(EXTRA_BMI, bmi);
        intent.putExtra(EXTRA_DIAGNOSIS, diagnosis);

        startActivity(intent);
    }

    // endregion
}
