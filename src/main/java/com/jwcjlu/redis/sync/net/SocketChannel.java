package com.jwcjlu.redis.sync.net;

import java.io.IOException;
import java.net.SocketAddress;

public interface SocketChannel {

     void write(byte[]... buf) throws IOException;

     byte[] read(int readSize) throws IOException;

     byte[] read(int readSize, int timeout) throws IOException;

     void read(byte[] data, int off, int len, int timeout) throws IOException;

     boolean isConnected();

     SocketAddress getRemoteSocketAddress();

     SocketAddress getLocalSocketAddress();

     void close();
     byte[]read()throws  IOException;
}
