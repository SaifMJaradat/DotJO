package com.jo.assignmentdotjo.base;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.jo.assignmentdotjo.app.App;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public abstract class BaseViewModel extends AndroidViewModel {

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    public final Context getContext() {
        return App.getAppContext();
    }

    public abstract void start();

    public abstract void stop();
}
