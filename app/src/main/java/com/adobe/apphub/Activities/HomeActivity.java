package com.adobe.apphub.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.adobe.apphub.Base.BaseActivity;
import com.adobe.apphub.Fragments.HomeFragment;
import com.adobe.apphub.R;

public class HomeActivity extends BaseActivity {

    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.hFragment);
    }

}
