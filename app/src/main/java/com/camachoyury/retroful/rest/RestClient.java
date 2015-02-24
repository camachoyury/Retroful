package com.camachoyury.retroful.rest;

import retrofit.RestAdapter;

/**
 * Created by yury on 2/23/15.
 */
public class RestClient {


    // poner la URL del servidor donde esta corriendo retroful-server
    private static final String BASE_URL = "http://192.168.2.100:8080/api";

    public RestAdapter getConnection(){

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .build();

        return restAdapter;
    }

}
