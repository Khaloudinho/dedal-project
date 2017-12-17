package fr.miage.m2.storage.persistkits.jdbc;

import fr.miage.m2.storage.highscores.HighScoreKitJDBC;
import fr.miage.m2.storage.persistkits.PersistKit;

public class JDBCKit implements PersistKit {

    private HighScoreKitJDBC highScoreKit = new HighScoreKitJDBC();

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
