package fr.miage.m2.storage.persistkits.json;

import java.io.Serializable;

/**
 * Class wich correspond to an highScore representation
 */
public class HighScoreJSONModel implements Serializable {

    private String username;
    private Integer score;

    public HighScoreJSONModel() {

    }

    public HighScoreJSONModel(String username, Integer score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}