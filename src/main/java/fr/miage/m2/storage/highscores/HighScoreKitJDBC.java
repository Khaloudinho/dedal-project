package fr.miage.m2.storage.highscores;

import fr.miage.m2.storage.persistkits.jdbc.EntityManager;

/**
 * HighScore for JDBC
 */
public class HighScoreKitJDBC extends HighScore {

    /**
     * Method which give the storage system info
     *
     * @return what storage system is
     */
    @Override
    public String info() {
        return "I am JDBC storage system !";
    }

    /**
     * Method which call EntityManager in order to get the high score
     *
     * @param username concerned user
     * @return high score
     */
    @Override
    public Integer getUserHighScoreByUserName(String username) {
        Integer userHighScore = EntityManager.getInstance().getUserHighScoreByUserName(username);
        return userHighScore;
    }

    /**
     * Method which call EntityManager in order to save or not the high score candidate
     *
     * @param username concerned user
     * @param score    possible high score
     */
    @Override
    public void saveHighScore(String username, Integer score) {
        EntityManager.createOrUpdateHighScore(username, score);
    }
}
