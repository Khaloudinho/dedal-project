package fr.miage.m2.storage.highscores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.miage.m2.storage.persistkits.json.HighScoreJSONModel;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

/**
 * HighScore for JSON
 */
public class HighScoreKitJSON extends HighScore {

    // Allow user to work with JSON format in very simple way
    private final Gson gson = new GsonBuilder().create();

    /**
     * Method which give the storage system info
     * @return what storage system is
     */
    @Override
    public String info(){
        return "I am JSON storage system !";
    }

    /**
     * Method which map all save scores in file in objects
     * @return list of mapped pbjects
     */
    private ArrayList<HighScoreJSONModel> getHighScoreFromJSON() {
        Type collectionType = new TypeToken<Collection<HighScoreJSONModel>>() {
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

    /**
     * Sub method which save in file high scores (JSON format)
     * @param scores we want save
     */
    private void saveScores(ArrayList<HighScoreJSONModel> scores) {
        // Get Content (in JSON)
        // Serialize objects
        String json = gson.toJson(scores);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter("highscores.json", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Write data
        writer.println(json);
        writer.close();
    }

    /**
     * Method which call sub methods in order to get the high score
     * @param username concerned user
     * @return high score
     */
    @Override
    public Integer getUserHighScoreByUserName(String username) {
        ArrayList<HighScoreJSONModel> scores = getHighScoreFromJSON();
        return getUserHighScore(scores, username);
    }

    /**
     * Method which get the high score of a given user
     * @param scores avaiable scores for all users
     * @param username concerned user
     * @return
     */
    private Integer getUserHighScore(ArrayList<HighScoreJSONModel> scores, String username){
        for (HighScoreJSONModel highScoreJSON:
             scores) {
            // Fast way to give the result
            if(highScoreJSON.getUsername().equals(username))
                return highScoreJSON.getScore();
        }
        return 0;
    }

    /**
     * Method which update high score or add a new user with his high score
     * @param scores map data from json (objects)
     * @param username concerned user
     * @param currentHighScore possible high score
     * @return
     */
    private ArrayList<HighScoreJSONModel> setUserHighScore(ArrayList<HighScoreJSONModel> scores, String username, Integer currentHighScore){
        // Use in order to improve iteration speed
        boolean match = false;

        // Iterate over save high scores
        for (HighScoreJSONModel highScoreJSON:
             scores) {
            // If user already exists
            if(highScoreJSON.getUsername().equals(username)) {
                Integer previousHighScore = highScoreJSON.getScore();
                if(previousHighScore<currentHighScore)
                    highScoreJSON.setScore(currentHighScore);
                match = true;
            }

            // Stop iterate if match
             if (match)
                 break;
        }

        // If user is new add him
        if (!match)
            scores.add(new HighScoreJSONModel(username, currentHighScore));

        return scores;
    }

    /**
     * Method which call subMethods in order to save or not the high score candidate
     * @param username concerned user
     * @param score possible high score
     */
    @Override
    public void saveHighScore(String username, Integer score){
        // Mapp JSON to objects
        ArrayList<HighScoreJSONModel> scores = getHighScoreFromJSON();

        // Update or create new high score for a given user
        setUserHighScore(scores, username, score);

        // Renregistrer
        saveScores(scores);
    }


}
