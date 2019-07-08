package com.topcoder.autoinsurance.View.Activities;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.Activity;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.topcoder.autoinsurance.R;
import com.topcoder.autoinsurance.controller.IResult;
import com.topcoder.autoinsurance.controller.VolleyCallback;
import com.topcoder.autoinsurance.utils.BottomNavigationViewHelper;


public class MainActivity extends Activity implements View.OnClickListener {

    private TextView textPolicy;
    private TextView textPolicyStart;
    private TextView textPolicyEnd;
    private TextView textCar;
    private TextView textDriver;
    private TextView textMonthly;
    private TextView textUsageAdjustment;
    private TextView textCurrentUsage;
    private TextView textNextDue;
    private TextView textDriverPersona;
    private TextView textDriverName;
    private TextView textDriverLicense;
    private TextView textDriverOtherName;
    private TextView textDriverOtherLicense;

    IResult mResultcallback=null;
    VolleyCallback mvolleyCallback;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home: {
                    View layoutDriver = findViewById(R.id.layout_driver);
                    View layoutPolicy = findViewById(R.id.layout_policy);
                    View layoutStats = findViewById(R.id.layout_stats);
                    layoutDriver.setVisibility(View.GONE);
                    layoutPolicy.setVisibility(View.VISIBLE);
                    layoutStats.setVisibility(View.VISIBLE);
                }
                break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

       final String URL=getString(R.string.LogiTechURL);

        
        initVolleyCallback();
        mvolleyCallback=new VolleyCallback(mResultcallback,this);
        mvolleyCallback.DataVolley(URL);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationViewHelper.removeShiftMode(navigation);

        View layoutPolicyDriver = findViewById(R.id.layout_policy_driver);
        layoutPolicyDriver.setOnClickListener(this);

        textPolicy = findViewById(R.id.text_policy);
        textPolicyStart = findViewById(R.id.text_policy_start);
        textPolicyEnd = findViewById(R.id.text_policy_end);
        textCar = findViewById(R.id.text_car);
        textDriver = findViewById(R.id.text_policy_driver);
        textMonthly = findViewById(R.id.text_stats_monthly);
        textUsageAdjustment = findViewById(R.id.text_usage_adjustment);
        textCurrentUsage = findViewById(R.id.text_current_usage);
        textNextDue = findViewById(R.id.text_next_due);
        textDriverPersona = findViewById(R.id.text_policy_driver_persona);
        textDriverName = findViewById(R.id.text_driver_name);
        textDriverLicense = findViewById(R.id.text_driver_license);
        textDriverOtherName = findViewById(R.id.text_driver_other_name);
        textDriverOtherLicense = findViewById(R.id.text_driver_other_license);
        initFont();
    }

    protected void initVolleyCallback() {

        mResultcallback=new IResult() {
            @Override
            public void OnSuccess(String Title) {

                 if (Title!=null) {
                     // textPolicy.setText("Policy#"+vehicleid);
                     textCar.setText(Title);
                 }
            }
        };
    }

    private void initFont() {
        Typeface typeSansProBold = Typeface.createFromAsset(getAssets(),"font/SourceSansPro-Bold.ttf");
        Typeface typeSansProMedium = Typeface.createFromAsset(getAssets(),"font/SourceSansPro-SemiBold.ttf");
        Typeface typeSansProBook = Typeface.createFromAsset(getAssets(),"font/SourceSansPro-Regular.ttf");
        textPolicy.setTypeface(typeSansProBold);

        textPolicyStart.setTypeface(typeSansProMedium);
        textPolicyEnd.setTypeface(typeSansProMedium);
        textCar.setTypeface(typeSansProMedium);
        textDriver.setTypeface(typeSansProMedium);
        textDriverPersona.setTypeface(typeSansProMedium);

        textDriverName.setTypeface(typeSansProMedium);
        textDriverLicense.setTypeface(typeSansProMedium);
        textDriverOtherName.setTypeface(typeSansProMedium);
        textDriverOtherLicense.setTypeface(typeSansProMedium);

        textMonthly.setTypeface(typeSansProBook);
        textUsageAdjustment.setTypeface(typeSansProBook);
        textCurrentUsage.setTypeface(typeSansProBook);
        textNextDue.setTypeface(typeSansProBook);
        TextView textPolicyStartLabel = findViewById(R.id.text_policy_start_label);
        TextView textPolicyEndLabel = findViewById(R.id.text_policy_end_label);
        TextView textCarLabel = findViewById(R.id.text_car_label);
        TextView textDriverLabel = findViewById(R.id.text_policy_driver_label);
        TextView textDriverPersonaLabel = findViewById(R.id.text_policy_driver_persona_label);

        TextView textDriverDetailsLabel = findViewById(R.id.text_driver_details_label);
        TextView textDriverPrimaryLabel = findViewById(R.id.text_driver_primary_label);
        TextView textDriverNameLabel = findViewById(R.id.text_driver_name_label);
        TextView textDriverLicenseLabel = findViewById(R.id.text_driver_license_label);
        TextView textDriverOtherNameLabel = findViewById(R.id.text_driver_other_name_label);
        TextView textDriverOtherLicenseLabel = findViewById(R.id.text_driver_other_license_label);
        TextView textDriverOtherLabel = findViewById(R.id.text_driver_other_label);

        textPolicyStartLabel.setTypeface(typeSansProMedium);
        textPolicyEndLabel.setTypeface(typeSansProMedium);
        textCarLabel.setTypeface(typeSansProMedium);
        textDriverLabel.setTypeface(typeSansProMedium);
        textDriverPersonaLabel.setTypeface(typeSansProMedium);

        textDriverDetailsLabel.setTypeface(typeSansProMedium);
        textDriverPrimaryLabel.setTypeface(typeSansProMedium);
        textDriverNameLabel.setTypeface(typeSansProMedium);
        textDriverLicenseLabel.setTypeface(typeSansProMedium);
        textDriverOtherNameLabel.setTypeface(typeSansProMedium);
        textDriverOtherLicenseLabel.setTypeface(typeSansProMedium);
        textDriverOtherLabel.setTypeface(typeSansProMedium);

        TextView textMonthlyLabel = findViewById(R.id.text_stats_monthly_label);
        TextView textUsageAdjustmentLabel = findViewById(R.id.text_usage_adjustment_label);
        TextView textCurrentUsageLabel = findViewById(R.id.text_current_usage_label);
        TextView textNextDueLabel = findViewById(R.id.text_next_due_label);

        textMonthlyLabel.setTypeface(typeSansProBold);
        textUsageAdjustmentLabel.setTypeface(typeSansProBold);
        textCurrentUsageLabel.setTypeface(typeSansProBold);
        textNextDueLabel.setTypeface(typeSansProBold);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_policy_driver: {
                View layoutDriver = findViewById(R.id.layout_driver);
                View layoutPolicy = findViewById(R.id.layout_policy);
                View layoutStats = findViewById(R.id.layout_stats);
                layoutDriver.setVisibility(View.VISIBLE);
                layoutPolicy.setVisibility(View.GONE);
                layoutStats.setVisibility(View.GONE);
            }
            break;
        }
    }
}
