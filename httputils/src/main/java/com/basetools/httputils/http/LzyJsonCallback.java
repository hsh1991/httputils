package com.basetools.httputils.http;

import android.util.Log;

import com.basetools.httputils.model.LzyResponse;
import com.basetools.httputils.model.SimpleResponse;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by han on 18-5-2.
 */

public abstract class LzyJsonCallback<T> extends AbsCallback<T> {
    private static final String TAG = "LzyJsonCallback";

    //成功标示
    private static final int SUCCESS_CODE = 200;

    @Override
    public T convertAction(String response) throws Exception {
        String responseString = response;

        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type type = params[0];

        if (!(type instanceof ParameterizedType)) throw new IllegalStateException("没有填写泛型参数");

        Type rawType = ((ParameterizedType) type).getRawType();

        //这里我们既然都已经拿到了泛型的真实类型，即对应的 class ，那么当然可以开始解析数据了，我们采用 Gson 解析
        //以下代码是根据泛型解析数据，返回对象，返回的对象自动以参数的形式传递到 onSuccess 中，可以直接使用

        JsonReader jsonReader = new JsonReader(new StringReader(responseString));
        if (rawType == Void.class) {
            //无数据类型,表示没有data数据的情况（以  new DialogCallback<LzyResponse<Void>>(this)  以这种形式传递的泛型)
            SimpleResponse simpleResponse = GsonConvert.fromJson(jsonReader, SimpleResponse.class);
            //noinspection unchecked
            return (T) simpleResponse.toLzyResponse();
        } else if (rawType == LzyResponse.class) {
            //有数据类型，表示有data
            LzyResponse lzyResponse = GsonConvert.fromJson(jsonReader, type);
            int code = lzyResponse.code;
            Log.i(TAG, "convertAction: code ==" + code);
            if (code == SUCCESS_CODE) {
                // TODO: 18-5-2 返回状态码逻辑
                return (T) lzyResponse;
            } else {
                throw new IllegalStateException(lzyResponse.msg);
            }

        } else {
            throw new IllegalStateException("基类错误无法解析!");
        }
    }
}
