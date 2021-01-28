package com.jo.assignmentdotjo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jo.assignmentdotjo.R;
import com.jo.assignmentdotjo.adapters.holders.ReposViewHolder;
import com.jo.assignmentdotjo.callbacks.ItemClickListener;
import com.jo.assignmentdotjo.databinding.ReposItemBinding;
import com.jo.assignmentdotjo.network.models.Repos;
import com.jo.assignmentdotjo.utilites.ItemAnimation;

import java.util.List;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public class ReposAdapter extends RecyclerView.Adapter<ReposViewHolder> {

    private List<Repos> reposList;
    private int animation_type = 0;
    private ItemClickListener itemClickListener;

    @NonNull
    @Override
    public ReposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ReposItemBinding reposItemBinding = ReposItemBinding.inflate(layoutInflater, parent, false);

        return new ReposViewHolder(reposItemBinding, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ReposViewHolder holder, int position) {
        Repos event = reposList.get(position);

        holder.bind(event);

        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.repos_item;
    }

    @Override
    public int getItemCount() {
        if (reposList != null) {
            return reposList.size();
        }
        return 0;
    }

    public Repos getClickedRepos(int position) {
        return reposList.get(position);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setReposList(List<Repos> repos) {
        this.reposList = repos;
        notifyDataSetChanged();
    }

    public void setAnimation_type(int animation_type) {
        this.animation_type = animation_type;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        super.onAttachedToRecyclerView(recyclerView);
    }

    private int lastPosition = -1;
    private boolean on_attach = true;

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, on_attach ? position : -1, animation_type);
            lastPosition = position;
        }
    }
}
