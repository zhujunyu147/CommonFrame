package com.zjy.frame.api;

import com.zjy.frame.dashboard.AlarmValueResponse;
import com.zjy.frame.dashboard.GetLocationResponse;
import com.zjy.frame.splash.DeviceListResonse;

import org.json.JSONObject;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiServer {
    @POST("user")
    Observable<String> LoginByRx(@Field("username") String username, @Field("password") String password);

    @Headers({"Content-type: application/json"})
    @POST("user")
    Observable<JSONObject> Login(@Body RequestBody bodyAsJson);

    @Headers({"Content-type: application/json"})
    @POST("user/data/set")
    Observable<JSONObject> SetLanguage( @Body RequestBody bodyAsJson);



    @Headers({"Content-type: application/json"})
    @POST("user/data/get")
    Observable<AlarmValueResponse> GetAlarmValue(@Body RequestBody bodyAsJson);


    @Headers({"Content-type: application/json"})
    @POST("user/device/location")
    Observable<GetLocationResponse> GetLocation(@Body RequestBody bodyAsJson);

    @GET("user/device/list")
    Observable<DeviceListResonse> GetBindDevice();

    @GET("/weather/token")
    Observable<JSONObject> GetWeatherToken();

}
