package com.topcoder.autoinsurance;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.Activity;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.topcoder.autoinsurance.domain.model.Policy;
import com.topcoder.autoinsurance.domain.repository.UserRepository;
import com.topcoder.autoinsurance.domain.repository.callback.GetPolicyCallback;
import com.topcoder.autoinsurance.domain.repository.dummyimpl.UserRepositoryImpl;
import com.topcoder.autoinsurance.utils.BottomNavigationViewHelper;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends Activity implements View.OnClickListener,GetPolicyCallback {

    private TextView textUser;
    private TextView textCarTop;
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
    private TextView textStatus;

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

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationViewHelper.removeShiftMode(navigation);

        View layoutPolicyDriver = findViewById(R.id.layout_policy_driver);
        layoutPolicyDriver.setOnClickListener(this);

        UserRepository repository = new UserRepositoryImpl(this);
        repository.getPolicy(this);

        textUser = findViewById(R.id.text_user);
        textCarTop = findViewById(R.id.text_car_top);
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
        textStatus = findViewById(R.id.text_status);

        initFont();
    }

    private void initFont() {
        Typeface typeSansProBold = Typeface.createFromAsset(getAssets(),"font/SourceSansPro-Bold.ttf");
        Typeface typeSansProMedium = Typeface.createFromAsset(getAssets(),"font/SourceSansPro-SemiBold.ttf");
        Typeface typeSansProBook = Typeface.createFromAsset(getAssets(),"font/SourceSansPro-Regular.ttf");

        textUser.setTypeface(typeSansProBold);
        textCarTop.setTypeface(typeSansProBold);
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
        textStatus.setTypeface(typeSansProBook);

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

    @Override
    public void onPolicy(Policy policy) {
        textUser.setText(policy.getUsername());
        textCar.setText(policy.getCar());
        textCarTop.setText(policy.getCar());
        textPolicy.setText("Policy# " + policy.getPolicy());
        textDriver.setText(policy.getDrivers().get(0).getName());
        textMonthly.setText(policy.getBasePremium());
        textUsageAdjustment.setText(policy.getUsageAdjustment() + "%");
        textCurrentUsage.setText(policy.getCurrentUsage());
        textDriverPersona.setText(policy.getDriverPersona());
        textDriverName.setText(policy.getDrivers().get(0).getName());
        textDriverLicense.setText(policy.getDrivers().get(0).getLicense());
        textDriverOtherName.setText(policy.getDrivers().get(1).getName());
        textDriverOtherLicense.setText(policy.getDrivers().get(1).getLicense());
        textStatus.setText(policy.getStatus());

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        textPolicyStart.setText(dateFormat.format(policy.getPolicyStartDate()));
        textPolicyEnd.setText(dateFormat.format(policy.getPolicyEndDate()));
        textNextDue.setText(dateFormat.format(policy.getNextDue()));
    }
}
