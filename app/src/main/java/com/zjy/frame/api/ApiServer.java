package com.zjy.frame.api;

import org.json.JSONObject;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiServer {
    @POST("user")
    Observable<String> LoginByRx(@Field("username") String username, @Field("password") String password);

    @Headers({"Content-type: application/json"})
    @POST("user")
    Observable<String> Login(@Body RequestBody bodyAsJson);

}
