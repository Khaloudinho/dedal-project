package fr.miage.m2.storage;

public abstract class HighScore {

    abstract String info();
    abstract Integer getUserHighScoreByUserName(String username);
    abstract void saveHighScore(String username, Integer score);

}
