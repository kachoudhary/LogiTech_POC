package com.topcoder.autoinsurance.controller;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.topcoder.autoinsurance.domain.model.Getters;

import org.json.JSONException;
import org.json.JSONObject;

/* Created by Kartikey Choudhary */

public class VolleyCallback {

    IResult mresultCallback=null;
    Context mcontext;


    public VolleyCallback(IResult resultCallback, Context context) {
        mresultCallback=resultCallback;
        mcontext = context;
    }

     public void DataVolley(String url) {
         RequestQueue queue = Volley.newRequestQueue(mcontext);

         JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
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

                     Getters getsecurity = new Getters("Bengaluru", "India", temp, "Cloudy");
                     mresultCallback.OnSuccess(temp);
                     Log.v("JSON",getsecurity.getTemperature().toString());

                     //citytemperature=getsecurity.getTemperature();
                     //autoinsightsdata.add(getsecurity);

                 } catch (JSONException ex) {
                     Log.v("karchouerror","Error: "+ex.getLocalizedMessage());
                 }
             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Log.e("karchouerror", error.getMessage(),error);
             }
         });
         queue.add(jsonObjectRequest);
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

}