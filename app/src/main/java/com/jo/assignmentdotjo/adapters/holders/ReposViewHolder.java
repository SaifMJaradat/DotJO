package com.jo.assignmentdotjo.adapters.holders;

import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jo.assignmentdotjo.adapters.ReposAdapter;
import com.jo.assignmentdotjo.callbacks.ItemClickListener;
import com.jo.assignmentdotjo.databinding.ReposItemBinding;
import com.jo.assignmentdotjo.network.models.Repos;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public class ReposViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ItemClickListener itemClickListener;
    private ReposItemBinding reposItemBinding;

    public ReposViewHolder(@NonNull ReposItemBinding reposItemBinding, ItemClickListener itemClickListener) {
        super(reposItemBinding.getRoot());

        this.itemClickListener = itemClickListener;
        this.reposItemBinding = reposItemBinding;

        itemView.setOnClickListener(this);
    }

    public void bind(Repos repos) {
        reposItemBinding.setRepos(repos);
        reposItemBinding.executePendingBindings();
    }

    @Override
    public void onClick(View view) {
        if (itemClickListener != null)
            if (getAdapterPosition() != -1) {
                itemClickListener.onItemClick(new ReposAdapter(), view, getAdapterPosition());
            }
    }
}
