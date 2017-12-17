package fr.miage.m2.storage;

public class HighScoreKit extends HighScore {

    public String info(){
        return "I am JDBC storage system !";
    }

    public Integer getUserHighScoreByUserName(String username) {
        Integer userHighScore = EntityManager.getInstance().getUserHighScoreByUserName(username);
        System.out.println(username+" ="+ userHighScore);

        return userHighScore;
    }

    @Override
    void saveHighScore(String username, Integer score) {
        EntityManager.createOrUpdateHighScore(username, score);
    }
}
