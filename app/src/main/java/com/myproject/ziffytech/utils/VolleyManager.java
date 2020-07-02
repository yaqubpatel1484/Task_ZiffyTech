package com.myproject.ziffytech.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

public class VolleyManager {

    public  VolleyManager(Context context, String url) {

        RequestQueue mRequestQueue = VolleyRequestQueue.getInstance(context).getRequestQueue();
             StringRequest request = new StringRequest(Request.Method.GET,
                     url,
                     new SuccessListener(),
                     new FailureListener());

            mRequestQueue.add(request);

    }

    private class SuccessListener implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {
            mOnResponseListener.onResponseReceived(response);

        }
    }

    private static class FailureListener implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {

            Log.e("Error--", error.toString());
        }
    }


    public interface OnResponseListener{
         void onResponseReceived(String json);
    }

    private OnResponseListener mOnResponseListener;

    public void ResponseListener(OnResponseListener onResponseListener)
    {
        mOnResponseListener = onResponseListener;
    }

}
