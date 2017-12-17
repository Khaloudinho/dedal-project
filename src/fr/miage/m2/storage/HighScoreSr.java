package fr.miage.m2.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class HighScoreSr extends HighScore {

    private final Gson gson = new GsonBuilder().create();

    public String info(){
        return "I am SR !";
    }

    private ArrayList<HighScoreJSON> getHighScoreFromJSON() {
        Type collectionType = new TypeToken<Collection<HighScoreJSON>>() {
        }.getType();

        String path = "highscores.json";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return gson.fromJson(bufferedReader, collectionType);
    }

    private void saveScores(ArrayList<HighScoreJSON> scores) {
        String json = gson.toJson(scores);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter("highscores.json", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println(json);
        writer.close();
    }

    @Override
    public Integer getUserHighScoreByUserName(String username) {
        ArrayList<HighScoreJSON> scores = getHighScoreFromJSON();
        return getUserHighScore(scores, username);
    }

    private Integer getUserHighScore(ArrayList<HighScoreJSON> scores, String username){
        for (HighScoreJSON highScoreJSON:
             scores) {
            if(highScoreJSON.getUsername().equals(username))
                return highScoreJSON.getScore();
        }
        return 0;
    }

    private ArrayList<HighScoreJSON> setUserHighScore(ArrayList<HighScoreJSON> scores, String username, Integer currentHighScore){
        boolean match = false;
        //Si l'utilisateur existe deja
        for (HighScoreJSON highScoreJSON:
             scores) {
            if(highScoreJSON.getUsername().equals(username)) {
                Integer previousHighScore = highScoreJSON.getScore();
                if(previousHighScore<currentHighScore)
                    highScoreJSON.setScore(currentHighScore);
                match = true;
            }
             if (match)
                 break;
        }

        if (!match)
            scores.add(new HighScoreJSON(username, currentHighScore));

        return scores;
    }

    public void saveHighScore(String username, Integer score){
        //Mapper le contenu du fichier JSON
        ArrayList<HighScoreJSON> scores = getHighScoreFromJSON();
        setUserHighScore(scores, username, score);

        //Renregistrer
        saveScores(scores);
    }


}
