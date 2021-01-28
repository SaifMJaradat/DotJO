package com.jo.assignmentdotjo.home.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jo.assignmentdotjo.R;
import com.jo.assignmentdotjo.adapters.ReposAdapter;
import com.jo.assignmentdotjo.base.BaseFragment;
import com.jo.assignmentdotjo.callbacks.InternetListener;
import com.jo.assignmentdotjo.callbacks.ItemClickListener;
import com.jo.assignmentdotjo.databinding.FragmentHomeBinding;
import com.jo.assignmentdotjo.home.MainViewModel;
import com.jo.assignmentdotjo.network.models.Repos;
import com.jo.assignmentdotjo.utilites.ItemAnimation;
import com.jo.assignmentdotjo.utilites.Tools;
import com.jo.assignmentdotjo.utilites.Transactions;

import java.util.List;

import static com.jo.assignmentdotjo.helpers.FragmentExtKt.obtainViewModelForActivity;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public class HomeFragment extends BaseFragment<MainViewModel, FragmentHomeBinding> implements InternetListener, ItemClickListener {

    private static final String TAG = "HomeFragment";

    private ReposAdapter reposAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public MainViewModel createViewModel() {
        return obtainViewModelForActivity(this, MainViewModel.class);
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInternetListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupReposRecyclerView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void setupReposRecyclerView() {
        reposAdapter = new ReposAdapter();
        reposAdapter.setItemClickListener(this);
        viewDataBinding.recyclerviewRepos.setLayoutManager(new LinearLayoutManager(getContext()));
        viewDataBinding.recyclerviewRepos.setAdapter(reposAdapter);
    }

    private void getRepos() {
        viewModel.getRepos().observe(this, repos -> {
            reposAdapter.setReposList(repos);
            viewModel.hideLoading();
        });
    }

    @Override
    public void internetConnected() {
        getRepos();
    }

    @Override
    public void internetNotConnected() {
        Tools.showShortMessage(getString(R.string.no_internet_connection));
    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, View view, int position) {
        if (adapter instanceof ReposAdapter) {
            Repos repos = reposAdapter.getClickedRepos(position);
            if (getFragmentManager() != null)
                Transactions.replaceFragmentWithAnimation(DetailsFragment.newInstance(repos), R.id.container, true, getFragmentManager());
        }
    }
}
