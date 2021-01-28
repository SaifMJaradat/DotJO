package com.jo.assignmentdotjo.home;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.jo.assignmentdotjo.R;
import com.jo.assignmentdotjo.base.BaseActivity;
import com.jo.assignmentdotjo.databinding.ActivityMainBinding;
import com.jo.assignmentdotjo.home.fragments.HomeFragment;
import com.jo.assignmentdotjo.utilites.Transactions;

import static com.jo.assignmentdotjo.helpers.AppCompatActivityExtKt.obtainViewModel;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> {

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel createViewModel() {
        return obtainViewModel(this, MainViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbarBack();
        if (savedInstanceState == null) {
            openHomeFragment();
        }
    }

    private void openHomeFragment() {
        Transactions.replaceFragmentInActivity(HomeFragment.newInstance(), R.id.container, true, getSupportFragmentManager());
    }

    private void setupToolbarBack() {
        setSupportActionBar(viewDataBinding.homeToolbar);
        viewDataBinding.homeToolbar.setNavigationOnClickListener(view -> onBackPressed());
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            try {
                Fragment fragmentById = getSupportFragmentManager().findFragmentById(R.id.container);

                String fragmentName = fragmentById != null ? fragmentById.getClass().getSimpleName() : "";
                handleNavigationBack(fragmentById);

                Log.e("BackStack ", " : " + fragmentName);

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        });
    }

    private void handleNavigationBack(Fragment fragmentById) {
        if (fragmentById instanceof HomeFragment) {
            if (getSupportActionBar() != null)
                getSupportActionBar().show();
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        } else {
            if (getSupportActionBar() != null)
                getSupportActionBar().show();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}