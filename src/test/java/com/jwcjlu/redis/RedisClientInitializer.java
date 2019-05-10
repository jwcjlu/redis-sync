package com.jwcjlu.redis;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.redis.RedisArrayAggregator;
import io.netty.handler.codec.redis.RedisBulkStringAggregator;
import io.netty.handler.codec.redis.RedisDecoder1;
import io.netty.handler.codec.redis.RedisEncoder;

public class RedisClientInitializer extends ChannelInitializer<Channel> {
    private Listener listener;
    public RedisClientInitializer(Listener listener){
        this.listener=listener;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new RedisDecoder1());
        pipeline.addLast(new RedisBulkStringAggregator());
        pipeline.addLast(new RedisArrayAggregator());
        pipeline.addLast(new RedisEncoder());
        pipeline.addLast(new RedisClientHandler(listener));
    }
}
