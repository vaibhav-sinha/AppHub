package com.adobe.apphub.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.adobe.apphub.Base.BaseActivity;
import com.adobe.apphub.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startHomeActivity();
    }

    private void startHomeActivity() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
    }

}
