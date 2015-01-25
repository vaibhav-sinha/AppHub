package com.adobe.apphub.Modules;

import android.content.Context;

import com.adobe.apphub.Activities.HomeActivity;
import com.adobe.apphub.Activities.ProductDetailsActivity;
import com.adobe.apphub.Activities.SplashActivity;
import com.adobe.apphub.Datastore.Cache;
import com.adobe.apphub.Datastore.Server;
import com.adobe.apphub.Fragments.HomeFragment;
import com.adobe.apphub.Fragments.ProductDetailsFragment;
import com.adobe.apphub.Helpers.ApiService;
import com.adobe.apphub.Helpers.NetworkAccessHelper;
import com.adobe.apphub.Helpers.SharedPreferencesHelper;
import com.adobe.apphub.Volley.LoadAppListRequest;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

/**
 * Created by vaisinha on 25/01/15.
 */

@Module(
        injects = {
                NetworkAccessHelper.class,
                LoadAppListRequest.class,
                Server.class,
                SplashActivity.class,
                HomeActivity.class,
                HomeFragment.class,
                ProductDetailsActivity.class,
                ProductDetailsFragment.class,
                ApiService.class,
                Cache.class
        },
        library = true,
        complete = true
)
public class MiddlewareGraph {

    private Context applicationContext;

    public MiddlewareGraph(Context context) {
        this.applicationContext = context;
    }

    @Provides
    public Context provideApplicationContext() {
        return applicationContext;
    }

    @Provides @Singleton
    EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @Provides @Singleton
    public RequestQueue provideRequestQueue() {
        return Volley.newRequestQueue(this.applicationContext);
    }

    @Provides @Singleton
    public NetworkAccessHelper provideNetworkAccessHelper() {
        return new NetworkAccessHelper();
    }

    @Provides @Singleton
    public LoadAppListRequest provideLoadAppListRequest() {
        return new LoadAppListRequest();
    }

    @Provides @Singleton
    public Server provideServer() {
        return new Server();
    }

    @Provides @Singleton
    public Cache provideCache() {
        return new Cache();
    }

    @Provides @Singleton
    public ApiService provideApiService() {
        return new ApiService();
    }

    @Provides @Singleton
    public SharedPreferencesHelper provideSharedPreferencesHelper() {
        return new SharedPreferencesHelper();
    }
}
