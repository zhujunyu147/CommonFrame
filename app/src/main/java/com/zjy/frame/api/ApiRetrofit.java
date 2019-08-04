package com.zjy.frame.api;

import android.util.Log;

import com.zjy.frame.app.IAQApplication;
import com.zjy.frame.base.BaseConverterFactory;
import com.zjy.frame.utils.Constants;
import com.zjy.frame.utils.PreferenceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class ApiRetrofit {
    public final String BASE_SERVER_URL = "https://iaq.honcloud.honeywell.com.cn/v2/00100002/";
    private static ApiRetrofit apiRetrofit;
    private ApiServer apiServer;
    private Retrofit retrofit;
    private OkHttpClient client;
    private String TAG = "ApiRetrofit2";

    public static ApiRetrofit getInstance() {
        if (apiRetrofit == null) {
            synchronized (Object.class) {
                if (apiRetrofit == null) {
                    apiRetrofit = new ApiRetrofit();
                }
            }
        }
        return apiRetrofit;
    }

    public ApiServer getApiService() {
        return apiServer;
    }


    public ApiRetrofit() {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);//Level中还有其他等级
        client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
                .addInterceptor(logging)
                .cookieJar(cookieJar)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVER_URL)
                .addConverterFactory(BaseConverterFactory.create())
                //支持RxJava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        apiServer = retrofit.create(ApiServer.class);
    }

    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
    private CookieJar cookieJar = new CookieJar() {
        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            cookieStore.put(url.host(), cookies);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = cookieStore.get(url.host());
            return cookies != null ? cookies : new ArrayList<Cookie>();
        }
    };


    HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            Log.e(TAG, message);
        }
    });


    /**
     * 请求访问request
     * response拦截器
     */
    private Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long startTime = System.currentTimeMillis();
            Response response = chain.proceed(chain.request());
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            String cookie = response.header("Set-Cookie");

            MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            Log.e(TAG, "----------Request Start----------------");
            Log.e(TAG, "| Request: " + request.toString() + request.headers().toString());
            Log.e(TAG, "| Response:" + cookie);
            Log.e(TAG, "| Response:" + content);
            Log.e(TAG, "----------Request End:" + duration + "毫秒----------");
            return response.newBuilder()
                    .body(ResponseBody.create(mediaType, content))
                    .build();
        }
    };


}
