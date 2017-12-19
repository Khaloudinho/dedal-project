package fr.miage.m2.storage.highscores;

import fr.miage.m2.storage.persistkits.jdbc.EntityManager;

/**
 * HighScore for JDBC
 */
public class HighScoreKitJDBC extends HighScore {

    public String info(){
        return "I am JDBC storage system !";
    }

    public Integer getUserHighScoreByUserName(String username) {
        Integer userHighScore = EntityManager.getInstance().getUserHighScoreByUserName(username);
        return userHighScore;
    }

    @Override
    public void saveHighScore(String username, Integer score) {
        EntityManager.createOrUpdateHighScore(username, score);
    }
}
