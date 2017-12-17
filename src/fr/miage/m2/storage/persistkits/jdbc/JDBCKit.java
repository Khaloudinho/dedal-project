package fr.miage.m2.storage.persistkits.jdbc;

import fr.miage.m2.storage.highscores.HighScoreKit;
import fr.miage.m2.storage.persistkits.PersistKit;

public class JDBCKit implements PersistKit {

    private HighScoreKit highScoreKit = new HighScoreKit();

    @Override
    public void save(String username, Integer highScore) {
        highScoreKit.saveHighScore(username, highScore);
    }

    @Override
    public Integer getUserHighScoreByUserName(String username) {
        return highScoreKit.getUserHighScoreByUserName(username);
    }

    @Override
    public void info() {
        System.out.println("JDBC storage");
    }
}
