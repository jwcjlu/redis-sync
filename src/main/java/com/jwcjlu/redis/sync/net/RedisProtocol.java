package com.jwcjlu.redis.sync.net;

/**
 * redis  rsep protocol
 *
 * In RESP, the type of some data depends on the first byte:
 *
 * For Simple Strings the first byte of the reply is "+"
 * For Errors the first byte of the reply is "-"
 * For Integers the first byte of the reply is ":"
 * For Bulk Strings the first byte of the reply is "$"
 * For Arrays the first byte of the reply is "*"
 */
public class RedisProtocol {
    public static final String PLUS="+";
    public static final String MINUS="-";
    public static final String COLON=":";
    public static final String DOLLAR="$";
    public static final String STAR="*";
    public static final String CRLF="\\r\\n";

}
