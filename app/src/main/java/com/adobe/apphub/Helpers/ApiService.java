package com.adobe.apphub.Helpers;

import android.content.Context;

import com.adobe.apphub.Base.BaseClass;
import com.adobe.apphub.Datastore.Cache;
import com.adobe.apphub.Datastore.Server;

import javax.inject.Inject;

/**
 * Created by vaisinha on 25/01/15.
 */
public class ApiService extends BaseClass {

    @Inject
    Server server;
    @Inject
    Cache cache;

    public void loadAppList(Context context) {
        if(cache.isAppListAvailable(context)) {
            cache.loadAppList(context);
        }
        else {
            server.loadAppList(context);
        }
    }
}
