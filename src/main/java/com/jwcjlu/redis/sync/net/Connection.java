package com.jwcjlu.redis.sync.net;

public interface Connection {
    /**
     *
     * @param configuration
     */
     void connect(Configuration configuration);

    /**
     *
     */
    void close();

    /**
     * 打开通道
     * @param configuration
     */

     void open(Configuration configuration);

}
