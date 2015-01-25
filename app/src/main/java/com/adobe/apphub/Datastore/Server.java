package com.adobe.apphub.Datastore;

import android.content.Context;

import com.adobe.apphub.Base.BaseClass;
import com.adobe.apphub.Volley.LoadAppListRequest;

import javax.inject.Inject;

/**
 * Created by vaisinha on 25/01/15.
 */
public class Server extends BaseClass {
    @Inject
    LoadAppListRequest loadAppListRequest;

    public void loadAppList(Context context) {
        loadAppListRequest.processRequest(context);
    }
}
