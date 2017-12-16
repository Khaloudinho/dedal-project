package fr.miage.m2.storage;

public interface PersistKit {

    HighScore save();

    Integer getUserHighScoreByUserName(String username);

}
