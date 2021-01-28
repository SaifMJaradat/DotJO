package com.jo.assignmentdotjo.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.jo.assignmentdotjo.BR;
import com.jo.assignmentdotjo.callbacks.InternetListener;
import com.jo.assignmentdotjo.internet.NetworkChangeReceiver;
import com.jo.assignmentdotjo.internet.NetworkObservable;

import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public abstract class BaseFragment<V extends BaseViewModel, T extends ViewDataBinding> extends Fragment implements Observer {

    private static final String TAG = "BaseFragment";

    public abstract int getLayoutId();

    public abstract V createViewModel();

    protected T viewDataBinding;
    protected V viewModel;

    private BroadcastReceiver broadcastReceiver;
    private InternetListener internetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        initReceiver();
        performDataBinding(inflater, container);

        return viewDataBinding.getRoot();
    }

    private void performDataBinding(LayoutInflater inflater, ViewGroup container) {

        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        viewModel = createViewModel();
        viewDataBinding.setVariable(BR.viewModel, viewModel);
        viewDataBinding.executePendingBindings();
    }

    public void setInternetListener(InternetListener internetListener) {
        this.internetListener = internetListener;
    }

    private void initReceiver() {
        broadcastReceiver = new NetworkChangeReceiver();
    }

    private void registerNetworkBroadcast() {
        Objects.requireNonNull(getContext()).registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private void unregisterNetworkChanges() {
        try {
            Objects.requireNonNull(getContext()).unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onResume() {
        viewModel.start();
        super.onResume();
        registerNetworkBroadcast();
        NetworkChangeReceiver.getObservable().addObserver(this);
    }

    @Override
    public void onPause() {
        viewModel.stop();
        super.onPause();
        NetworkChangeReceiver.getObservable().deleteObserver(this);
        unregisterNetworkChanges();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof NetworkObservable) {

            try {
                if (NetworkChangeReceiver.getObservable().isConnected()) {
                    if (internetListener != null)
                        internetListener.internetConnected();
                } else {
                    if (internetListener != null)
                        internetListener.internetNotConnected();
                }
            } catch (Exception ex) {
                Log.e(TAG, "update: " + ex);
            }
        }
    }
}