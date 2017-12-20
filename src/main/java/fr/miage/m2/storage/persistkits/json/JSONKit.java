package fr.miage.m2.storage.persistkits.json;

import fr.miage.m2.storage.highscores.HighScoreKitJSON;
import fr.miage.m2.storage.persistkits.PersistKit;

/**
 * PersistKit class for JSON
 */
public class JSONKit implements PersistKit {

    HighScoreKitJSON highScoreSr = new HighScoreKitJSON();

    /**
     * Method which call HighScoreKit.. in order to it
     * @param username name of the concerned user
     * @param highScore possible high score
     */
    @Override
    public void save(String username, Integer highScore) {
        highScoreSr.saveHighScore(username, highScore);
    }

    /**
     * Method which call the HighScoreKit..
     * @param username name of the concerned user
     * @return high score
     */
    @Override
    public Integer getUserHighScoreByUserName(String username) {
        return highScoreSr.getUserHighScoreByUserName(username);
    }

    /**
     * Method which give info the name about current Kit
     */
    @Override
    public void info() {
        System.out.println("JSON storage");
    }
}
