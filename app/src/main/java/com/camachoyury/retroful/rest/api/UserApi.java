package com.camachoyury.retroful.rest.api;

import com.camachoyury.retroful.rest.model.User;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by yury on 2/23/15.
 */
public interface UserApi {


    @GET("/user/")
    User getUser(@Query("username") String username);

    @POST("/user/")
    User login(@Query("username") String username, @Query("password") String password);

}
