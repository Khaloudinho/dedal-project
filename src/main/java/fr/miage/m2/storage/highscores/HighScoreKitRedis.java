package fr.miage.m2.storage.highscores;

import fr.miage.m2.storage.persistkits.redis.JedisConnection;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

/**
 * HighScore for Redis
 */
public class HighScoreKitRedis extends HighScore {

    // Connection in order to interact with redis
    Jedis jedis = JedisConnection.getDbCon().getConn();
    // Redis key for a hasmap like
    private String hmsetKey = "scores";

    public String info() {
        return "I am Redis storage system !";
    }

    @Override
    public Integer getUserHighScoreByUserName(String username) {
        String score = jedis.hmget(hmsetKey + ":" + username, "score").get(0);

        // Case when there a no highscore
        if (score == null)
            return 0;

        return Integer.parseInt(score);
    }

    public void saveHighScore(String username, Integer score) {

        Integer previousHighScore = getUserHighScoreByUserName(username);

        // Only if we have a highScore
        if (previousHighScore < score) {
            // Association
            String hscore = hmsetKey + ":" + username;

            // Data about the user
            HashMap<String, String> donnees = new HashMap<String, String>();
            donnees.put("utilisateur", username);
            donnees.put("score", score.toString());

            // Save in redis
            jedis.hmset(hscore, donnees);
            System.out.println(username + " : " + score + "(highscore redis)");
        }
    }


}
