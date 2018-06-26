package com.topcoder.autoinsurance.domain.repository.dummyimpl.runnable;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.topcoder.autoinsurance.R;
import com.topcoder.autoinsurance.domain.executor.BaseRunnable;
import com.topcoder.autoinsurance.domain.model.Policy;
import com.topcoder.autoinsurance.domain.repository.callback.GetPolicyCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class GetPolicyRunnable implements BaseRunnable {

    private GetPolicyCallback mCallback;
    private Context mContext;
    private Policy mPolicy;

    public GetPolicyRunnable(Context context, GetPolicyCallback callback) {
        mContext = context;
        mCallback = callback;
    }

    @Override
    public void run() {
        InputStream is = mContext.getResources().openRawResource(R.raw.data);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            is.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonString = writer.toString();
        try {
            Policy dataHolder = new ObjectMapper()
                    .readValue(jsonString, Policy.class);
            mPolicy = dataHolder;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish() {
        mCallback.onPolicy(mPolicy);
    }
}
