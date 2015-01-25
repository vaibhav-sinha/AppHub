package com.adobe.apphub.Helpers;

import android.content.Context;

import com.adobe.apphub.Base.BaseClass;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

import javax.inject.Inject;

/**
 * Created by vaisinha on 25/01/15.
 */
public class NetworkAccessHelper extends BaseClass {

    @Inject
    Context applicationContext;
    @Inject
    RequestQueue requestQueue;

    public void submitNetworkRequest(String requestTag, Request request) {
        requestQueue.cancelAll(requestTag);
        requestQueue.add(request);
        request.setTag(requestTag);
    }
}
