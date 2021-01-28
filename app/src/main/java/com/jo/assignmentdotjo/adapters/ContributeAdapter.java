package com.jo.assignmentdotjo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jo.assignmentdotjo.R;
import com.jo.assignmentdotjo.adapters.holders.ContributeViewHolder;
import com.jo.assignmentdotjo.adapters.holders.ReposViewHolder;
import com.jo.assignmentdotjo.callbacks.ItemClickListener;
import com.jo.assignmentdotjo.databinding.ContributeItemBinding;
import com.jo.assignmentdotjo.databinding.ReposItemBinding;
import com.jo.assignmentdotjo.network.models.Contribute;
import com.jo.assignmentdotjo.network.models.Repos;
import com.jo.assignmentdotjo.utilites.ItemAnimation;

import java.util.List;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public class ContributeAdapter extends RecyclerView.Adapter<ContributeViewHolder> {

    private List<Contribute> contributesList;
    private int animation_type = 0;
    private ItemClickListener itemClickListener;

    @NonNull
    @Override
    public ContributeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ContributeItemBinding contributeItemBinding = ContributeItemBinding.inflate(layoutInflater, parent, false);

        return new ContributeViewHolder(contributeItemBinding, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContributeViewHolder holder, int position) {
        Contribute contribute = contributesList.get(position);

        holder.bind(contribute);

        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.contribute_item;
    }

    @Override
    public int getItemCount() {
        if (contributesList != null) {
            return contributesList.size();
        }
        return 0;
    }

    public Contribute getClickedContribute(int position) {
        return contributesList.get(position);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setContributesList(List<Contribute> contributesList) {
        this.contributesList = contributesList;
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
