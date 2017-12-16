package fr.miage.m2.storage;

public class JdbcKit implements PersistKit {

    private HighScoreKit highScoreKit = new HighScoreKit();

    @Override
    public HighScore save() {
        return new HighScoreKit();
    }

    @Override
    public Integer getUserHighScoreByUserName(String username) {
        return highScoreKit.getUserHighScoreByUserName(username);
    }
}
