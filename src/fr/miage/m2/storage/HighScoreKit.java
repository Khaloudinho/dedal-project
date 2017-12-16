package fr.miage.m2.storage;

public class HighScoreKit extends HighScore {

    public String info(){
        return "I am JDBC !";
    }

    public Integer getUserHighScoreByUserName(String username) {
        Integer userHighScore = EntityManager.getInstance().getUserHighScoreByUserName("Said");
        System.out.println(username+" ="+ userHighScore);

        return userHighScore;
    }
}
