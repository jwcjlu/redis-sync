package com.jwcjlu.redis.sync.net;

import lombok.Data;

import java.net.SocketAddress;
import java.util.Properties;
@Data
public class Configuration {
    private String replId;
    private Properties properties;
    private SocketAddress address;
    private Configuration(){
    }
    public static Configuration defaultSetting(){
        return new Configuration();
    }


    public void setReplId(String s) {
    }

    public void setReplOffset(long parseLong) {
    }

    public String getReplId() {
        return replId;
    }

    public SocketAddress getSocketAddress() {
        return address;
    }
}
