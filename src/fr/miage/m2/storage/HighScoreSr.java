package fr.miage.m2.storage;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.util.Pair;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

public class HighScoreSr extends HighScore {

    private final Gson gson = new GsonBuilder().create();

    public String info(){
        return "I am SR !";
    }

    @Override
    public Integer getUserHighScoreByUserName(String username) {
        Type collectionType = new TypeToken<Collection<HighScoreJSON>>() {
        }.getType();

        String path = "highscores.json";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        ArrayList<HighScoreJSON> scores = gson.fromJson(bufferedReader, collectionType);

        return getUserHighScore(scores, username);
    }

    private Integer getUserHighScore(ArrayList<HighScoreJSON> scores, String username){
        for (HighScoreJSON highScoreJSON:
             scores) {
            if(highScoreJSON.getUsername().equals(username))
                return highScoreJSON.getScore();
                break;
        }
        return 0;
    }
}
