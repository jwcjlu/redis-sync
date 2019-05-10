package com.jwcjlu.redis.sync.net;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.concurrent.atomic.AtomicBoolean;
@Slf4j
public class RedisConnection {
    private Configuration configuration;
    private SocketChannel channel;
    private AtomicBoolean  conncted=new AtomicBoolean(false);
    public RedisConnection(Configuration configuration){
        this.configuration=configuration;
    }
    public void connect(){
        if(conncted.compareAndSet(false,true)){
            SocketAddress sd=configuration.getSocketAddress();

            try {
                channel=BioSocketChannelPool.open(sd);
            } catch (Exception e) {
                log.error("open addrees failure !!!",e);
            }
        }
    }
    protected void establishConnection() throws IOException {
        connect();
        sendPing();
     /*   sendSlavePort();
        sendSlaveIp();
        sendSlaveCapa("eof");
        sendSlaveCapa("psync2");*/
    }

    protected void sendPing() throws IOException {
        log.info("PING");
        channel.write("PING".getBytes());
        final String reply = "";//Strings.toString(reply());
        log.info(reply);
        if ("PONG".equalsIgnoreCase(reply)) return;
        if (reply.contains("NOAUTH")) throw new AssertionError(reply);
        if (reply.contains("operation not permitted")) throw new AssertionError("-NOAUTH Authentication required.");
        log.warn("[PING] failed. {}", reply);
    }
    public void close(){

    }
    private byte[] reply(int byteSize){
        try {
            return channel.read(byteSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }





}
