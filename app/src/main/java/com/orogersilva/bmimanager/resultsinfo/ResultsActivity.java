package com.orogersilva.bmimanager.resultsinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.orogersilva.bmimanager.R;
import com.orogersilva.bmimanager.calcinfo.CalcActivity;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by orogersilva on 11/14/2016.
 */

public class ResultsActivity extends AppCompatActivity {

    // region FIELDS

    private final String TAG = "ResultsActivity";

    @BindView(R.id.bmiResultMessageTextView)
    TextView bmiResultMessageTextView;
    @BindView(R.id.diagnosisMessageTextView)
    TextView diagnosisMessageTextView;

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        double bmi = intent.getDoubleExtra(CalcActivity.EXTRA_BMI, 0.0);
        String diagnosisMessage = intent.getStringExtra(CalcActivity.EXTRA_DIAGNOSIS);

        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        bmiResultMessageTextView.append(" " + decimalFormat.format(bmi) + ".");
        diagnosisMessageTextView.append(" " + diagnosisMessage + "!");
    }

    // endregion
}
