package com.basetools.httputils.http;

/**
 * Created by han on 18-5-2.
 */

public abstract class StringCallback extends AbsCallback<String> {


    @Override
    public String convertAction(String result) throws Exception {
        return result;
    }
}
