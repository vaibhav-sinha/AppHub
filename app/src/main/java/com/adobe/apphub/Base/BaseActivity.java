package com.adobe.apphub.Base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.adobe.apphub.Application.AppHubApplication;
import com.adobe.apphub.Helpers.WindowAnimationHelper;

/**
 * Created by vaisinha on 25/01/15.
 */
public class BaseActivity extends FragmentActivity {

    protected Boolean dontUseAnimation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppHubApplication)getApplication()).inject(this);
    }


    @Override
    public void finish() {
        super.finish();
        if(!dontUseAnimation) {
            WindowAnimationHelper.finish(this);
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if(dontUseAnimation) {
            super.startActivityForResult(intent, requestCode);
        }
        else {
            WindowAnimationHelper.startActivityForResultWithSlideFromRight(this, intent, requestCode);
        }
    }

    @Override
    public void startActivity(Intent intent) {
        if(dontUseAnimation) {
            super.startActivity(intent);
        }
        else {
            WindowAnimationHelper.startActivityWithSlideFromRight(this, intent);
        }
    }
}
