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
import com.jo.assignmentdotjo.adapters.ContributeAdapter;
import com.jo.assignmentdotjo.adapters.ReposAdapter;
import com.jo.assignmentdotjo.base.BaseFragment;
import com.jo.assignmentdotjo.callbacks.InternetListener;
import com.jo.assignmentdotjo.callbacks.ItemClickListener;
import com.jo.assignmentdotjo.config.Const;
import com.jo.assignmentdotjo.databinding.FragmentDetailsBinding;
import com.jo.assignmentdotjo.home.MainViewModel;
import com.jo.assignmentdotjo.network.models.Contribute;
import com.jo.assignmentdotjo.network.models.Repos;
import com.jo.assignmentdotjo.utilites.ItemAnimation;
import com.jo.assignmentdotjo.utilites.Tools;

import java.util.List;

import static com.jo.assignmentdotjo.helpers.FragmentExtKt.obtainViewModelForActivity;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public class DetailsFragment extends BaseFragment<MainViewModel, FragmentDetailsBinding> implements InternetListener, ItemClickListener {

    private static final String TAG = "DetailsFragment";
    private ContributeAdapter contributeAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_details;
    }

    @Override
    public MainViewModel createViewModel() {
        return obtainViewModelForActivity(this, MainViewModel.class);
    }

    public static DetailsFragment newInstance(Repos repos) {

        DetailsFragment detailsFragment = new DetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(Const.REPOS_KEY, repos);
        detailsFragment.setArguments(bundle);

        return detailsFragment;
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
        contributeAdapter = new ContributeAdapter();
        contributeAdapter.setAnimation_type(ItemAnimation.FADE_IN);
        contributeAdapter.setItemClickListener(this);
        viewDataBinding.recyclerviewContributors.setLayoutManager(new LinearLayoutManager(getContext()));
        viewDataBinding.recyclerviewContributors.setAdapter(contributeAdapter);
    }

    private void getClickedRepos() {
        if (getArguments() != null) {
            Repos repos = getArguments().getParcelable(Const.REPOS_KEY);
            viewDataBinding.setRepos(repos);
            viewModel.getContributes(repos.getContributorsUrl()).observe(this, contributes -> {
                contributeAdapter.setContributesList(contributes);
                viewModel.hideLoading();
            });
        }
    }

    @Override
    public void internetConnected() {
        getClickedRepos();
    }

    @Override
    public void internetNotConnected() {
        Tools.showShortMessage(getString(R.string.no_internet_connection));
    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, View view, int position) {
        if (adapter instanceof ContributeAdapter) {

        }
    }
}
