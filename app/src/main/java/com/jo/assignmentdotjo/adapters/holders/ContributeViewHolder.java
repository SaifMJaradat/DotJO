package com.jo.assignmentdotjo.adapters.holders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jo.assignmentdotjo.adapters.ContributeAdapter;
import com.jo.assignmentdotjo.adapters.ReposAdapter;
import com.jo.assignmentdotjo.callbacks.ItemClickListener;
import com.jo.assignmentdotjo.databinding.ContributeItemBinding;
import com.jo.assignmentdotjo.databinding.ReposItemBinding;
import com.jo.assignmentdotjo.network.models.Contribute;
import com.jo.assignmentdotjo.network.models.Repos;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public class ContributeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ItemClickListener itemClickListener;
    private ContributeItemBinding contributeItemBinding;

    public ContributeViewHolder(@NonNull ContributeItemBinding contributeItemBinding, ItemClickListener itemClickListener) {
        super(contributeItemBinding.getRoot());

        this.itemClickListener = itemClickListener;
        this.contributeItemBinding = contributeItemBinding;

        itemView.setOnClickListener(this);
    }

    public void bind(Contribute contribute) {
        contributeItemBinding.setContribute(contribute);
        contributeItemBinding.setProgressBar(contributeItemBinding.progressBar);
        contributeItemBinding.executePendingBindings();
    }

    @Override
    public void onClick(View view) {
        if (itemClickListener != null)
            if (getAdapterPosition() != -1) {
                itemClickListener.onItemClick(new ContributeAdapter(), view, getAdapterPosition());
            }
    }
}
