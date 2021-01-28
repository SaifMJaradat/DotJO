package com.jo.assignmentdotjo.internet;

import com.jo.assignmentdotjo.utilites.Tools;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public class NetworkConnectionInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        if (!Tools.isNetworkAvailable()) {
            throw new NoConnectivityException();
        }

        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }

}