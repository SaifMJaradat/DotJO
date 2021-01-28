package com.jo.assignmentdotjo.internet;

import java.util.Observable;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public class NetworkObservable extends Observable {

    private static NetworkObservable instance = null;
    private static boolean network;

    private NetworkObservable() {

    }

    void connectionChanged(boolean connection) {
        network = connection;
        setChanged();
        notifyObservers(connection);
    }

    public boolean isConnected()
    {
        return network;
    }

    static NetworkObservable getInstance() {
        if (instance == null) {
            instance = new NetworkObservable();
        }
        return instance;
    }
}