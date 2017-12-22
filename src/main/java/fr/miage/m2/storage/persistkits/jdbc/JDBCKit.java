package fr.miage.m2.storage.persistkits.jdbc;

import fr.miage.m2.storage.highscores.HighScoreKitJDBC;
import fr.miage.m2.storage.persistkits.PersistKit;

/**
 * Class which implements PersistKit for JDBC/Postgres
 */
public class JDBCKit implements PersistKit {

    private HighScoreKitJDBC highScoreKit = new HighScoreKitJDBC();

    /**
     * Method which calls HighScoreKit.. in order to it
     *
     * @param username  name of the concerned user
     * @param highScore possible high score
     */
    @Override
    public void save(String username, Integer highScore) {
        highScoreKit.saveHighScore(username, highScore);
    }

    /**
     * Method which calls the HighScoreKit..
     *
     * @param username name of the concerned user
     * @return high score
     */
    @Override
    public Integer getUserHighScoreByUserName(String username) {
        return highScoreKit.getUserHighScoreByUserName(username);
    }

    /**
     * Method which gives info the name about current Kit
     */
    @Override
    public void info() {
        System.out.println("JDBC storage");
    }
}
