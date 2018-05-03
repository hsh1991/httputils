package com.basetools.httputils.http;

import android.util.Log;

import com.basetools.httputils.model.MsgModel;
import com.google.gson.stream.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by han on 18-5-2.
 */

public abstract class JsonCallback<T> extends AbsCallback<T> {

    @Override
    public T convertAction(String response) throws Exception {
        String responseString = response;

        Type genType = getClass().getGenericSuperclass();
//        //从上述的类中取出真实的泛型参数，有些类可能有多个泛型，所以是数值
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type type = params[0];

        JsonReader jsonReader = new JsonReader(new StringReader(responseString));

        return GsonConvert.fromJson(jsonReader, type);
    }
}
