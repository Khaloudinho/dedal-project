package fr.miage.m2.storage.persistkits;

public interface PersistKit {

    void save(String username, Integer highScore);

    Integer getUserHighScoreByUserName(String username);

    void info();
}
