package com.jo.assignmentdotjo.network;


import com.jo.assignmentdotjo.config.Const;
import com.jo.assignmentdotjo.network.models.Contribute;
import com.jo.assignmentdotjo.network.models.Repos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public interface ApiService {

    @GET(Const.REPOS)
    Call<List<Repos>> getRepos();

    @GET
    Call<List<Contribute>> getContributes(@Url String url);


}
