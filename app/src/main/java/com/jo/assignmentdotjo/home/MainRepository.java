package com.jo.assignmentdotjo.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.jo.assignmentdotjo.base.BaseRepository;
import com.jo.assignmentdotjo.network.models.Contribute;
import com.jo.assignmentdotjo.network.models.Repos;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
class MainRepository extends BaseRepository {

    private static final String TAG = "MainRepository";

    private static MainRepository mainRepository;

    private MainRepository() {

    }

    public static MainRepository getInstance() {
        if (mainRepository == null) {
            mainRepository = new MainRepository();
        }
        return mainRepository;
    }

    public MutableLiveData<List<Repos>> getRepos() {

        final MutableLiveData<List<Repos>> repos = new MutableLiveData<>();

        Call<List<Repos>> reposCall = apiService.getRepos();

        reposCall.enqueue(new Callback<List<Repos>>() {
            @Override
            public void onResponse(Call<List<Repos>> call, Response<List<Repos>> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        repos.setValue(response.body());
                    }else {
                        Log.e(TAG, "onResponse: response body is null.");
                    }
                }else {
                    Log.e(TAG, "onResponse: is not success " + response.code() + " : " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Repos>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
        return repos;
    }

    public MutableLiveData<List<Contribute>> getContributes(String url) {

        final MutableLiveData<List<Contribute>> contributes = new MutableLiveData<>();

        Call<List<Contribute>> reposCall = apiService.getContributes(url);

        reposCall.enqueue(new Callback<List<Contribute>>() {
            @Override
            public void onResponse(Call<List<Contribute>> call, Response<List<Contribute>> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        contributes.setValue(response.body());
                    } else {
                        Log.e(TAG, "onResponse: response body is null.");
                    }
                } else {
                    Log.e(TAG, "onResponse: is not success " + response.code() + " : " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Contribute>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
        return contributes;
    }
}
