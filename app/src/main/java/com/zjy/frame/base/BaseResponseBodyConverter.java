package com.zjy.frame.base;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.zjy.frame.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class BaseResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    BaseResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        String jsonString = value.string();
        try {
            JSONObject object = new JSONObject(jsonString);
            if (object.has(Constants.RESPONSE_KEY_PHONEID)) {
                //登录成功结果
                return adapter.fromJson(jsonString);
            } else {

                return adapter.fromJson(jsonString);
            }


        } catch (JSONException e) {
            e.printStackTrace();
//            //数据解析异常
            throw new BaseException(BaseException.PARSE_ERROR_MSG, BaseException.PARSE_ERROR);
        } finally {
            value.close();
        }
    }
}
