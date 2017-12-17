package fr.miage.m2.storage;

public class JdbcKit implements PersistKit {

    private HighScoreKit highScoreKit = new HighScoreKit();

    @Override
    public void save(String username, Integer highScore) {
        highScoreKit.saveHighScore(username, highScore);
    }

    @Override
    public Integer getUserHighScoreByUserName(String username) {
        return highScoreKit.getUserHighScoreByUserName(username);
    }
}
