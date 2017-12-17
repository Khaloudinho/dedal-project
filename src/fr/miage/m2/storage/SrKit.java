package fr.miage.m2.storage;

public class SrKit implements PersistKit {

    HighScoreSr highScoreSr = new HighScoreSr();

    @Override
    public void save(String username, Integer highScore) {
        highScoreSr.saveHighScore(username, highScore);
    }

    @Override
    public Integer getUserHighScoreByUserName(String username) {
        return highScoreSr.getUserHighScoreByUserName(username);
    }
}
