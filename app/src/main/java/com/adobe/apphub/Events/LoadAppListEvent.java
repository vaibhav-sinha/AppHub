package com.adobe.apphub.Events;

import com.adobe.apphub.Base.BaseEvent;
import com.adobe.apphub.Models.AppData;

import java.util.List;

/**
 * Created by vaisinha on 25/01/15.
 */
public class LoadAppListEvent extends BaseEvent {

    private List<AppData> appDataList;

    public List<AppData> getAppDataList() {
        return appDataList;
    }

    public void setAppDataList(List<AppData> appDataList) {
        this.appDataList = appDataList;
    }
}
