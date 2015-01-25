package com.adobe.apphub.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.adobe.apphub.Base.BaseActivity;
import com.adobe.apphub.Events.ProductSelectedEvent;
import com.adobe.apphub.Fragments.HomeFragment;
import com.adobe.apphub.R;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

public class HomeActivity extends BaseActivity {

    @Inject
    EventBus eventBus;

    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.hFragment);
        eventBus.register(this);
    }

    @Override
    protected void onDestroy() {
        eventBus.unregister(this);
        super.onDestroy();
    }

    public void onEventMainThread(ProductSelectedEvent event) {
        if(event.getSuccess()) {
            Intent i = new Intent(this, ProductDetailsActivity.class);
            i.putExtra("PRODUCT", event.getAppData());
            startActivity(i);
        }
    }
}
