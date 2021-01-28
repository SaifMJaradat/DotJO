package com.jo.assignmentdotjo.internet;

import java.io.IOException;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "No Internet Connection";
    }
}