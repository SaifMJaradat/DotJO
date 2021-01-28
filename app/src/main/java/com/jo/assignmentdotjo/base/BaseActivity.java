package com.jo.assignmentdotjo.base;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.jo.assignmentdotjo.BR;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public abstract class BaseActivity<P extends BaseViewModel, T extends ViewDataBinding> extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    protected T viewDataBinding;
    protected P viewModel;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initBuilding();
    }

    private void initBuilding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutResourceId());
        viewModel = createViewModel();
        viewDataBinding.setVariable(BR.viewModel, viewModel);
    }

    protected abstract int getLayoutResourceId();

    protected abstract P createViewModel();

    public void showProgressDialog(String title, String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle(title);
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}

