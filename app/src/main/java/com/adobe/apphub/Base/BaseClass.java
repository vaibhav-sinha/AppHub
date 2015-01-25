package com.adobe.apphub.Base;

import com.adobe.apphub.Application.AppHubApplication;

/**
 * Created by vaisinha on 25/01/15.
 */
public class BaseClass {
    public BaseClass() {
        AppHubApplication.getInstance().inject(this);
    }
}
