package com.adobe.apphub.Application;

import android.app.Application;
import android.os.StrictMode;
import android.support.v7.appcompat.BuildConfig;
import android.util.Log;

import com.adobe.apphub.Modules.MiddlewareGraph;

import java.util.Arrays;

import dagger.ObjectGraph;

/**
 * Created by vaisinha on 25/01/15.
 */
public class AppHubApplication extends Application {

    private static ObjectGraph objectGraph;
    private static AppHubApplication instance;

    @Override
    public void onCreate() {
        if (BuildConfig.DEBUG) {
            Log.d("AppHubApplication", "Debug mode enabled");
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .penaltyDialog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }
        super.onCreate();
        instance = this;
        objectGraph = ObjectGraph.create(getModules());
    }

    private Object[] getModules() {
        return Arrays.asList(new MiddlewareGraph(this)).toArray();
    }

    public void inject(Object target) {
        objectGraph.inject(target);
    }

    public static AppHubApplication getInstance() {
        return instance;
    }
}
