package fr.miage.m2.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public final class EntityManager {

    static EntityManager entityManager;

    public static EntityManager getInstance(){
        if(entityManager == null) {
            entityManager = new EntityManager();
        }
        return entityManager;
    }



    public static Integer getUserHighScoreByUserName(String username) {
        Integer highScore = 0;
        try {
            PreparedStatement pstmt = PostgresConnection.getDbCon().conn.prepareStatement("SELECT score FROM highscores WHERE username = ?;");
            pstmt.setString(1,username);

            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                highScore = resultSet.getInt("score");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return highScore;
    }
}
