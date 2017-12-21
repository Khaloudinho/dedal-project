package fr.miage.m2.storage.persistkits.redis;

import redis.clients.jedis.Jedis;

public final class JedisConnection {

    private static final String host = "localhost";
    private static final int port = 6379;
    private static final int redisDB = 2;
    public static JedisConnection db;
    private Jedis conn;

    private JedisConnection() {
        conn = new Jedis(host, port);
        conn.select(redisDB);
    }

    /**
     * Create a Jedis connection with redis
     *
     * @return
     */
    public static synchronized JedisConnection getDbCon() {
        if (db == null) {
            db = new JedisConnection();
        }
        return db;
    }

    public Jedis getConn() {
        return conn;
    }
}

