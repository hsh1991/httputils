package com.basetools.httputils.http;

/**
 * Created by han on 18-5-1.
 */

public interface Converter<T> {
    /**
     * 拿到响应后，将数据转换成需要的格式，子线程中执行，可以是耗时操作
     *
     * @param result 需要转换的对象
     * @return 转换后的结果
     * @throws Exception 转换过程发生的异常
     */
    public T convertAction(String result) throws Exception;
}
