package com.jo.assignmentdotjo.internet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jo.assignmentdotjo.utilites.Tools;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    private static final String TAG = "NetworkChangeReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        try {

            getObservable().connectionChanged(Tools.isNetworkAvailable());

        } catch (Exception exception) {
            Log.e(TAG, "onReceive: ", exception);
        }
    }

    public static NetworkObservable getObservable() {
        return NetworkObservable.getInstance();
    }
}
