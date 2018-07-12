package com.topcoder.autoinsurance;

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


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.topcoder.autoinsurance.domain.model.Getters;
import com.topcoder.autoinsurance.domain.model.Policy;
import com.topcoder.autoinsurance.domain.repository.UserRepository;
import com.topcoder.autoinsurance.domain.repository.callback.GetPolicyCallback;
import com.topcoder.autoinsurance.domain.repository.dummyimpl.UserRepositoryImpl;
import com.topcoder.autoinsurance.utils.BottomNavigationViewHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    private final static String AutoInsightPOSTurl="http://54.152.74.58:8080/user/login";
    private final static String TestURL="http://api.openweathermap.org/data/2.5/weather?q=bengaluru&units=metric&appid=e730b8f2202b9f96e684c09f877baa38";
    private ArrayList<Getters> autoinsightsdata=new ArrayList<>();
    private Integer citytemperature;

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

        Volleytest();

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

    private void Volleytest() {

        final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, TestURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               Log.v("karchouresponse","RES: "+response.toString());

               try {
                   JSONObject main=response.getJSONObject("main");

                   Integer temp=main.getInt("temp");
                   Log.v("karchoutemp","Temp: "+temp);


                   /* JSONArray list=response.getJSONArray("list");

                   for (int x=0;x<5;x++) {
                       JSONObject obj=list.getJSONObject(x);
                       JSONObject main2=obj.getJSONObject("main");

                       Double currenttemp=main2.getDouble("temp");

                       }*/

                       Getters getsecurity=new Getters("Bengaluru","India",temp,"Cloudy");
                       Log.v("JSON",getsecurity.getTemperature().toString());
                       citytemperature=getsecurity.getTemperature();
                       autoinsightsdata.add(getsecurity);

               } catch (JSONException ex) {
                 Log.v("karchouerror","Error: "+ex.getLocalizedMessage());
               }
               updateUI();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("karchouerror", error.getMessage(),error);
            }
        });
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    private void updateUI() {
        if (autoinsightsdata.size()>0) {
            Getters temperature=autoinsightsdata.get(0);
            Log.d("karchouidiot","Temp is :"+temperature);
            textPolicy.setText("Bengaluru temp is: "+citytemperature+ " C");
        }
    }

/*    private void Postapicall() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);


        StringRequest postRequest = new StringRequest(Request.Method.POST, AutoInsightPOSTurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR", "error => " + error.toString());
            }
        })

        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer 621c34fbfc14f80cf8254e3a81e6e13616f68b4849b83e1f69c741ac355b2c8f7b7ceef0ee46e0792ab450237ffe3c88");
                headers.put("from", "AIzaSyC95iJPbZ6LhMC0FE7cQzB-O4BYjfCFJwg");
                headers.put("client-device-id", "348705222200");
                headers.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 2.3.3; en-us; HTC_DesireS_S510e Build/GRI40) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");

                return headers;
            }


            @Override
            public String getBodyContentType() {
                return "application/json";
            }


            @Override
            public byte[] getBody() throws AuthFailureError {
              Map<String,String> bodycontent=new HashMap<String, String>();
              bodycontent.put("email","admin@ai.com");
              bodycontent.put("password","123");
              return null;
            }
        };


        final JsonObjectRequest postRequest2=new JsonObjectRequest(Request.Method.POST, AutoInsightPOSTurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("karchou", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("karchouerror", error.getMessage(),error);
            }
        })  {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer 621c34fbfc14f80cf8254e3a81e6e13616f68b4849b83e1f69c741ac355b2c8f7b7ceef0ee46e0792ab450237ffe3c88");
                headers.put("from", "AIzaSyC95iJPbZ6LhMC0FE7cQzB-O4BYjfCFJwg");
                headers.put("client-device-id", "348705222200");
                headers.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 2.3.3; en-us; HTC_DesireS_S510e Build/GRI40) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");

                return headers;
            }

            @Override
            public byte[] getBody() {
                String str = "{\n" +
                        "  \"email\" :\"admin@ai.com\",\n" +
                        "  \"password\" : \"123\"\n" +
                        "} ";
                Log.d("karchoujson","JSON" +str.getBytes());
                return str.getBytes();

            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };
        requestQueue.add(postRequest2);
    }*/

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
        /*textPolicy.setText("Policy# " + policy.getPolicy()); */
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
