package fr.miage.m2.storage.persistkits.redis;

import redis.clients.jedis.Jedis;

public final class JedisConnection {

    public static JedisConnection db;

    private Jedis conn;

    //private static final String host = "localhost";
    private static final String host = "92.222.86.67";
    private static final int port = 6379;
    private static final int redisDB = 2;

    private JedisConnection() {
        conn = new Jedis(host, port);
        conn.auth("DNmunU6Ps6XV1x7B1Haao1w1kFKTjxAnEcQG7I80XkNtrSVTCzYV19cugakRXt2Lzpre5MpGEqaqxYDWmkPRVYT99JBBjcLtH4REKI0nmhdwO9GvFlMEdOO0s9KpSUa3pAIihDUKXiPo7h21Z0N5LtginwUe1Whpqjef1g3L7wuoZsTUU5qSFrtNPlMBmkYJNMyxX7BOGkDFBIwwE2Czr6DlGHmKkhTvUrvBUev0zmBhnZEVBYvkMwXAJeBJgU0T");
        conn.select(redisDB);
    }

    /**
     * Create a Jedis connection with redis
     * @return
     */
    public static synchronized JedisConnection getDbCon() {
        if ( db == null ) {
            db = new JedisConnection();
        }
        return db;
    }

    public Jedis getConn() {
        return conn;
    }
}

