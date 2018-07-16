package com.topcoder.autoinsurance.controller;


import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface IResult {
    public void notifySuccess(String requestType,JSONObject response);
    public void notifyError(String requestType,VolleyError error);
}
