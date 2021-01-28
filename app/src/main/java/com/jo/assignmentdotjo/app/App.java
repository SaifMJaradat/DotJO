package com.jo.assignmentdotjo.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public class App extends Application {

    private static final String TAG = "App";

    private static Context context;
    public static Resources resources;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        App.context = this;
        resources = getResources();
    }

    public static Context getAppContext() {
        return App.context;
    }
}
