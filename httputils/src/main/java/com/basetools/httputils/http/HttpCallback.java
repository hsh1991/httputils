package com.basetools.httputils.http;

/**
 * Created by han on 18-5-2.
 */

public abstract class HttpCallback {
    public abstract void onSuccess(String result);

    public abstract void onFailure(Exception e);
}