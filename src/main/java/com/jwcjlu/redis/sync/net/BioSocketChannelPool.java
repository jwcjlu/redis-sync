package com.jwcjlu.redis.sync.net;

import java.net.Socket;
import java.net.SocketAddress;

public class BioSocketChannelPool {
    public static BioSocketChannel open(SocketAddress address) throws Exception {
        Socket socket = new Socket();
        socket.setSoTimeout(BioSocketChannel.SO_TIMEOUT);
        socket.setTcpNoDelay(true);
        socket.setKeepAlive(true);
        socket.setReuseAddress(true);
        socket.connect(address, BioSocketChannel.DEFAULT_CONNECT_TIMEOUT);
        return new BioSocketChannel(socket);
    }
}
