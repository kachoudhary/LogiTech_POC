package com.topcoder.autoinsurance.controller;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

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
                     JSONArray list=response.getJSONArray("movies");
                     for (int x=0;x<=list.length();x++) {
                         JSONObject obj=list.getJSONObject(x);
                         String Title= (String) obj.get("Title");
                         URL imageURL=(URL) obj.get("Poster");
                         Log.v("karchoutemp","Temp: "+Title);
                         mresultCallback.OnSuccess(Title,imageURL);
                     }

                 } catch (JSONException ex) {
                     Log.v("karchouexception","Error: "+ex.getLocalizedMessage());
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
}