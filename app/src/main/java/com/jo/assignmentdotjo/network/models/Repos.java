package com.jo.assignmentdotjo.network.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public class Repos implements Parcelable {

    @SerializedName("name")
    private String name;

    @SerializedName("forks")
    private String numberOfForks;

    @SerializedName("watchers")
    private String numberOfWatchers;

    @SerializedName("contributors_url")
    private String contributorsUrl;


    protected Repos(Parcel in) {
        name = in.readString();
        numberOfForks = in.readString();
        numberOfWatchers = in.readString();
        contributorsUrl = in.readString();
    }

    public static final Creator<Repos> CREATOR = new Creator<Repos>() {
        @Override
        public Repos createFromParcel(Parcel in) {
            return new Repos(in);
        }

        @Override
        public Repos[] newArray(int size) {
            return new Repos[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberOfForks() {
        return numberOfForks;
    }

    public void setNumberOfForks(String numberOfForks) {
        this.numberOfForks = numberOfForks;
    }

    public String getNumberOfWatchers() {
        return numberOfWatchers;
    }

    public void setNumberOfWatchers(String numberOfWatchers) {
        this.numberOfWatchers = numberOfWatchers;
    }

    public String getContributorsUrl() {
        return contributorsUrl;
    }

    public void setContributorsUrl(String contributorsUrl) {
        this.contributorsUrl = contributorsUrl;
    }

    @Override
    public String toString() {
        return "Repos{" +
                "name='" + name + '\'' +
                ", numberOfForks='" + numberOfForks + '\'' +
                ", numberOfWatchers='" + numberOfWatchers + '\'' +
                ", contributorsUrl='" + contributorsUrl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(numberOfForks);
        parcel.writeString(numberOfWatchers);
        parcel.writeString(contributorsUrl);
    }
}
