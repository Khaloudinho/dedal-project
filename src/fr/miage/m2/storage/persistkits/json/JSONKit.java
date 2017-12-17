package fr.miage.m2.storage.persistkits.json;

import fr.miage.m2.storage.highscores.HighScoreKitJSON;
import fr.miage.m2.storage.persistkits.PersistKit;

/**
 * PersistKit class for JSON
 */
public class JSONKit implements PersistKit {

    HighScoreKitJSON highScoreSr = new HighScoreKitJSON();

    @Override
    public void save(String username, Integer highScore) {
        highScoreSr.saveHighScore(username, highScore);
    }

    @Override
    public Integer getUserHighScoreByUserName(String username) {
        return highScoreSr.getUserHighScoreByUserName(username);
    }

    @Override
    public void info() {
        System.out.println("JSON storage");
    }
}
