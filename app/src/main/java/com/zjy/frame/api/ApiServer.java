package com.zjy.frame.api;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface ApiServer {
    @POST("shopping_login.htm")
    Observable<String> LoginByRx(@Field("username") String username, @Field("password") String password);
}
