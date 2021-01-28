package com.jo.assignmentdotjo.callbacks;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public interface ItemClickListener {
    void onItemClick(RecyclerView.Adapter adapter , View view, int position);
}
