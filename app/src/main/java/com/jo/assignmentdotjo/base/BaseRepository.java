package com.jo.assignmentdotjo.base;

import com.jo.assignmentdotjo.network.ApiClient;
import com.jo.assignmentdotjo.network.ApiService;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public class BaseRepository {

    public ApiService apiService = ApiClient.getClient().create(ApiService.class);
}
