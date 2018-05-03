package com.basetools.httputils.http;

import android.app.Application;
import android.util.Log;

import java.util.Map;
import java.util.Objects;

/**
 * Created by han on 18-4-30.
 */

public class HttpUtils {
    private static final String TAG = "HttpUtils";

    private static Application context;

    private static IHttpProcessor httpProcessor = null;

    private boolean isDebug = false;


    private HttpUtils() {
        Log.i(TAG, "HttpUtils: init");

    }

    private static class HttpUtilsHolder {
        private static HttpUtils holder = new HttpUtils();
    }

    public static void init(IHttpProcessor processor) {
        httpProcessor = processor;
    }

    public static HttpUtils getInstance() {
        return HttpUtilsHolder.holder;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public static void post(String url, Map<String, Objects> params, final AbsCallback callBack) {
        HttpCallback httpcallback = new HttpCallback() {
            @Override
            public void onSuccess(String result) {
                if (getInstance().isDebug()) {
                    Log.i(TAG, "debug 网络返回数据为--->: " + result);
                }
                callBack.convert(result);
            }

            @Override
            public void onFailure(Exception e) {
                callBack.onError(e);

            }
        };
        httpProcessor.post(url, params, httpcallback);

    }

    public static void get(String url, Map<String, Objects> params, final AbsCallback callBack) {
        HttpCallback httpcallback = new HttpCallback() {
            @Override
            public void onSuccess(String result) {
                if (getInstance().isDebug()) {
                    Log.i(TAG, "debug 网络返回数据为--->: " + result);
                }
                callBack.convert(result);
            }

            @Override
            public void onFailure(Exception e) {
                callBack.onError(e);

            }
        };
        httpProcessor.get(url, params, httpcallback);
    }


}
