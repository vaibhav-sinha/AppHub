package com.adobe.apphub.Datastore;

import android.content.Context;
import android.util.Log;

import com.adobe.apphub.Base.BaseClass;
import com.adobe.apphub.Events.LoadAppListEvent;
import com.adobe.apphub.Helpers.SharedPreferencesHelper;
import com.adobe.apphub.Models.AppData;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by vaisinha on 25/01/15.
 */
public class Cache extends BaseClass {

    @Inject
    EventBus eventBus;
    @Inject
    SharedPreferencesHelper sharedPreferencesHelper;

    public void loadAppList(Context context) {
        String json = sharedPreferencesHelper.getString(context, "ServerData", "AppData", null);
        Gson gson = new Gson();
        try {
            List<AppData> appDataList;
            LoadAppListEvent loadAppListEvent = new LoadAppListEvent();
            appDataList = gson.fromJson(json, new TypeToken<ArrayList<AppData>>(){}.getType());
            loadAppListEvent.setSuccess(true);
            loadAppListEvent.setAppDataList(appDataList);
            eventBus.post(loadAppListEvent);
        } catch (JsonParseException e) {
            LoadAppListEvent loadAppListEvent = new LoadAppListEvent();
            loadAppListEvent.setSuccess(false);
            loadAppListEvent.setError("Invalid json");
            eventBus.post(loadAppListEvent);
        }
    }

    public Boolean isAppListAvailable(Context context) {
        if(sharedPreferencesHelper.getBoolean(context, "ServerData", "DataAvailable", false)) {
            if((new Date().getTime() - sharedPreferencesHelper.getLong(context, "ServerData", "DownloadTime", 0L)) < 7*24*60*60*1000) {
                return true;
            }
        }
        return false;
    }

    public void updateAppList(Context context, String json) {
        sharedPreferencesHelper.putString(context, "ServerData", "AppData", json);
        sharedPreferencesHelper.putLong(context, "ServerData", "DownloadTime", new Date().getTime());
        sharedPreferencesHelper.putBoolean(context, "ServerData", "DataAvailable", true);
    }
}
