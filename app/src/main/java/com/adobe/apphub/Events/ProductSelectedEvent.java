package com.adobe.apphub.Events;

import com.adobe.apphub.Base.BaseEvent;
import com.adobe.apphub.Models.AppData;

/**
 * Created by vaisinha on 25/01/15.
 */
public class ProductSelectedEvent extends BaseEvent {

    private AppData appData;

    public AppData getAppData() {
        return appData;
    }

    public void setAppData(AppData appData) {
        this.appData = appData;
    }
}
