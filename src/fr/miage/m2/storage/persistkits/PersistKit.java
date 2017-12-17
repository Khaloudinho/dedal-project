package fr.miage.m2.storage.persistkits;

/**
 * Interface wich describe what a persisKit implementation have to do
 * It is a simple way to have a common way to interact
 */
public interface PersistKit {

    void save(String username, Integer highScore);

    Integer getUserHighScoreByUserName(String username);

    void info();
}
