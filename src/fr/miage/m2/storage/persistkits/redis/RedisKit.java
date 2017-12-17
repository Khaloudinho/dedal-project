package fr.miage.m2.storage.persistkits.redis;

import fr.miage.m2.storage.highscores.HighScoreKitRedis;
import fr.miage.m2.storage.persistkits.PersistKit;

public class RedisKit implements PersistKit {

    private HighScoreKitRedis highScoreKitRedis = new HighScoreKitRedis();

    @Override
    public void save(String username, Integer highScore) {
        highScoreKitRedis.saveHighScore(username, highScore);
    }

    @Override
    public Integer getUserHighScoreByUserName(String username) {
        return highScoreKitRedis.getUserHighScoreByUserName(username);
    }

    @Override
    public void info() {
        System.out.println("Redis storage");
    }
}
