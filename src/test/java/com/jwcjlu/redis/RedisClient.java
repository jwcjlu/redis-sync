package com.jwcjlu.redis;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;

import java.lang.reflect.Array;
import java.util.concurrent.ArrayBlockingQueue;

public class RedisClient implements Listener {
    private Channel channel;
    String host;    //   目标主机
    int port;       //   目标主机端口
    private ArrayBlockingQueue queue=new ArrayBlockingQueue(10);

    public RedisClient(String host,int port){
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new RedisClientInitializer(this));

            channel = bootstrap.connect(host, port).sync().channel();
            System.out.println(" connected to host : " + host + ", port : " + port);
            System.out.println(" type redis's command to communicate with redis-server or type 'quit' to shutdown ");


    }
    public  void write(String request) throws InterruptedException {
        ChannelFuture lastWriteFuture = null;
        lastWriteFuture = channel.writeAndFlush(request);
        lastWriteFuture.addListener(new GenericFutureListener<ChannelFuture>() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    System.err.print("write failed: ");
                    future.cause().printStackTrace(System.err);
                }
            }
        });
        lastWriteFuture.sync();
        Object msg=queue.take();
        System.out.println(msg);

    }
    public void open(String cmd) throws InterruptedException {
        write(cmd);
       Object obj= queue.take();
        parseDump();

    }

    private void parseDump() {
    }


    public static void main(String[] args) throws Exception{
        System.out.println(new String(new byte[]{42, 49}));
        RedisClient client = new RedisClient("127.0.0.1",6379);
        client.start();
        client.write("PING");
        client.write("REPLCONF listening-port 2230");
        client.write(  "REPLCONF ip-address 192.168.1.105");
        client.write( "REPLCONF capa eof");
        client.write( "REPLCONF capa psync2");
        client.open("PSYNC 12344 -1");
    }


    @Override
    public void reply(Object msg) {
        queue.add(msg);
    }
}
