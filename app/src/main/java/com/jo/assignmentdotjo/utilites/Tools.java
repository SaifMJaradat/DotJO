package com.jo.assignmentdotjo.utilites;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jo.assignmentdotjo.R;
import com.jo.assignmentdotjo.app.App;
import com.jo.assignmentdotjo.config.Const;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public class Tools {

    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) App.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showShortMessage(String message) {
        Toast.makeText(App.getAppContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void setImage(ImageView image, String url, ProgressBar progressBar) {
        if (url != null) {
            Glide.with(App.getAppContext())
                    .load(url)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            image.setImageResource(R.drawable.ic_launcher_foreground);
                            if (progressBar != null)
                                progressBar.setVisibility(View.GONE);
                            return true;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            if (progressBar != null)
                                progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .fitCenter()
                    .into(image);
        }
    }
}
