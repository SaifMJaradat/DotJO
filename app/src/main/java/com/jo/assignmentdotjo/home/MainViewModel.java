package com.jo.assignmentdotjo.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import com.jo.assignmentdotjo.base.BaseViewModel;
import com.jo.assignmentdotjo.network.models.Contribute;
import com.jo.assignmentdotjo.network.models.Repos;

import java.util.List;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public class MainViewModel extends BaseViewModel {

    private static final String TAG = "MainViewModel";

    private final MainRepository mainRepository;
    public ObservableBoolean loading;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mainRepository = MainRepository.getInstance();
        loading = new ObservableBoolean();
    }

    public MutableLiveData<List<Repos>> getRepos() {
        return mainRepository.getRepos();
    }

    public MutableLiveData<List<Contribute>> getContributes(String url) {
        return mainRepository.getContributes(url);
    }

    @Override
    public void start() {
        showLoading();
        Log.e(TAG, "start: Start MainActivity View Model");
    }

    @Override
    public void stop() {
        Log.e(TAG, "stop: Stop MainActivity View Model");
    }

    public void showLoading() {
        loading.set(true);
    }

    public void hideLoading() {
        loading.set(false);
    }
}

