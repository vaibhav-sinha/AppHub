package com.adobe.apphub.Volley;

import android.content.Context;

import com.adobe.apphub.Base.BaseClass;
import com.adobe.apphub.Events.LoadAppListEvent;
import com.adobe.apphub.Helpers.NetworkAccessHelper;
import com.adobe.apphub.Models.AppData;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by vaisinha on 25/01/15.
 */
public class LoadAppListRequest extends BaseClass {

    @Inject
    EventBus eventBus;
    @Inject
    NetworkAccessHelper networkAccessHelper;

    public void processRequest(Context context) {
        StringRequest request = new StringRequest("http://adobe.0x10.info/api/products?type=json", createSuccessListener(context), createErrorListener(context));
        this.networkAccessHelper.submitNetworkRequest("GetAppList", request);
    }

    private Response.ErrorListener createErrorListener(final Context context) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LoadAppListEvent loadAppListEvent = new LoadAppListEvent();
                loadAppListEvent.setSuccess(false);
                loadAppListEvent.setError(error.toString());
                eventBus.post(loadAppListEvent);
            }
        };
    }

    private Response.Listener<String> createSuccessListener(final Context context) {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String json) {
                Gson gson = new Gson();
                try {
                    List<AppData> appDataList;
                    LoadAppListEvent loadAppListEvent = new LoadAppListEvent();
                    appDataList = gson.fromJson(json, new TypeToken<ArrayList<AppData>>(){}.getType());
                    loadAppListEvent.setSuccess(true);
                    loadAppListEvent.setAppDataList(appDataList);
                    eventBus.postSticky(loadAppListEvent);
                } catch (JsonParseException e) {
                    LoadAppListEvent loadAppListEvent = new LoadAppListEvent();
                    loadAppListEvent.setSuccess(false);
                    loadAppListEvent.setError("Invalid json");
                    eventBus.post(loadAppListEvent);
                }
            }
        };
    }
}
