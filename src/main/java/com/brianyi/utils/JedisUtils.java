package com.brianyi.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;


/**
 * @author ahao
 */
public class JedisUtils {
    private static JedisPoolConfig poolConfig = null;
    private static JedisPool jedisPool = null;
    private static Integer maxTotal = null;
    private static Integer maxIdle = null;
    private static String host = null;
    private static Integer port = null;
    private static String auth =null;
    static{

        //读取配置文件 获得参数值
        ResourceBundle rb = ResourceBundle.getBundle("jedis");
        maxTotal = Integer.parseInt(rb.getString("jedis.maxTotal"));
        maxIdle = Integer.parseInt(rb.getString("jedis.maxIdle"));
        port = Integer.parseInt(rb.getString("jedis.port"));
        host = rb.getString("jedis.host");
        auth = rb.getString("jedis.auth");
        poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxWaitMillis( 10 * 1000 );
        poolConfig.setTestOnBorrow(true);
        jedisPool = new JedisPool(poolConfig,host,port,10000,auth);
    }

    public static Jedis getJedis(){
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }
    /***
     *
     * 释放资源
     */
    public synchronized static void  returnResource(final Jedis jedis) {
        if(jedis != null) {
            jedis.close();
        }
    }

}
