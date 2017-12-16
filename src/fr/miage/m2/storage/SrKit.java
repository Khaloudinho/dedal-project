package fr.miage.m2.storage;

public class SrKit implements PersistKit {

    HighScoreSr highScoreSr = new HighScoreSr();

    @Override
    public HighScore save() {
        return new HighScoreSr();
    }

    @Override
    public Integer getUserHighScoreByUserName(String username) {
        return null;
    }
}
