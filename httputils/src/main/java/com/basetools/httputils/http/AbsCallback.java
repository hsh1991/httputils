package com.basetools.httputils.http;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by han on 18-5-1.
 */

public abstract class AbsCallback<T> implements Converter<T> {

    private static final String TAG = "AbsCallback";
    /**
     * 对返回数据进行操作的回调， UI线程
     */
    public abstract void onSuccess(T t);


    /**
     * 请求失败，响应错误，数据解析错误等，都会回调该方法， UI线程
     */
    public void onError(Exception e) {
        Log.e("abscallback ", "onError: " + e.toString());
    }


    public void convert(String request) {
        //在io线程转换
        AsyncTask<String, Void, T> asyncTask = new AsyncTask<String, Void, T>() {

            @Override
            protected T doInBackground(String... params) {
                String param = params[0];
                Log.i(TAG, "doInBackground: " + param);
                try {
                    T t = convertAction(param);
                    return t;
                } catch (Exception e) {
                    onError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T t) {
                onSuccess(t);
            }
        };
        asyncTask.execute(request);

    }

}
