package com.jo.assignmentdotjo.utilites;


import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.databinding.BindingAdapter;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public class BindingHelper {

    private static final String TAG = "BindingHelper";

    @BindingAdapter(value = {"imageUrl", "progressBar"}, requireAll = false)
    public static void setImage(ImageView imageView, String imageUrl, ProgressBar progressBar) {
        if (imageUrl != null)
            Tools.setImage(imageView, imageUrl, progressBar);
    }
}
