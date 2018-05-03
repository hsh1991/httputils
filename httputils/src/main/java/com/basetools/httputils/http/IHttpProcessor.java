package com.basetools.httputils.http;

import java.util.Map;

/**
 * Created by han on 18-5-1.
 */

public interface IHttpProcessor {

    void post(String url, Map params, HttpCallback callBack);

    void get(String url, Map params, HttpCallback callBack);
}
