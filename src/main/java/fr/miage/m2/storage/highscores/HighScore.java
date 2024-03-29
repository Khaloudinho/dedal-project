package fr.miage.m2.storage.highscores;

/**
 * HighScore abstract class which describes what a HighScore implementation can done
 */
public abstract class HighScore {
    abstract String info();

    abstract Integer getUserHighScoreByUserName(String username);

    abstract void saveHighScore(String username, Integer score);
}
