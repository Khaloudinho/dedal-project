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

    /**
     * Method which give the storage system info
     *
     * @return what storage system is
     */
    @Override
    public String info() {
        return "I am Redis storage system !";
    }

    /**
     * Method which implements the get of the high score
     *
     * @param username concerned user
     * @return high score
     */
    @Override
    public Integer getUserHighScoreByUserName(String username) {

        // Use the hasmap data
        String score = jedis.hmget(hmsetKey + ":" + username, "score").get(0);

        // Case when there a no highscore
        if (score == null)
            return 0;

        // Parse to integer the string return
        return Integer.parseInt(score);
    }

    /**
     * Method which save in file high scores
     *
     * @param username
     * @param score
     */
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
