package fr.miage.m2.storage.persistkits.redis;

import fr.miage.m2.storage.highscores.HighScoreKitRedis;
import fr.miage.m2.storage.persistkits.PersistKit;

/**
 * Class which implements PersistKit for Redis
 */
public class RedisKit implements PersistKit {

    private HighScoreKitRedis highScoreKitRedis = new HighScoreKitRedis();

    /**
     * Method which calls HighScoreKit.. in order to it
     *
     * @param username  name of the concerned user
     * @param highScore possible high score
     */
    @Override
    public void save(String username, Integer highScore) {
        highScoreKitRedis.saveHighScore(username, highScore);
    }

    /**
     * Method which calls the HighScoreKit..
     *
     * @param username name of the concerned user
     * @return high score
     */
    @Override
    public Integer getUserHighScoreByUserName(String username) {
        return highScoreKitRedis.getUserHighScoreByUserName(username);
    }

    /**
     * Method which gives info the name about current Kit
     */
    @Override
    public void info() {
        System.out.println("Redis storage");
    }
}
