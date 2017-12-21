package fr.miage.m2.storage.persistkits.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class which implements a simple singleton in order to have one connection to our mysql database
 */
public final class EntityManager {

    static EntityManager entityManager;

    public static EntityManager getInstance() {
        if (entityManager == null) {
            entityManager = new EntityManager();
        }
        return entityManager;
    }

    public static Integer getUserHighScoreByUserName(String username) {
        Integer highScore = 0;
        try {
            PreparedStatement pstmt = PostgresConnection.getDbCon().conn.prepareStatement("SELECT score FROM highscores WHERE username = ?;");
            pstmt.setString(1, username);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                highScore = resultSet.getInt("score");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return highScore;
    }

    public static void createOrUpdateHighScore(String username, Integer currentHighScore) {
        Integer previousHighScore = getUserHighScoreByUserName(username);
        boolean alreadyExistingUser = previousHighScore > 0;

        //update user's highscore
        if (alreadyExistingUser) {
            if (previousHighScore < currentHighScore) {
                try {
                    PreparedStatement pstmt = PostgresConnection.getDbCon().conn.prepareStatement("UPDATE highscores SET score = ? WHERE username = ?;");
                    pstmt.setInt(1, currentHighScore);
                    pstmt.setString(2, username);
                    pstmt.execute();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                PreparedStatement pstmt = PostgresConnection.getDbCon().conn.prepareStatement("INSERT INTO highscores (username, score) VALUES (?, ?);");
                pstmt.setString(1, username);
                pstmt.setInt(2, currentHighScore);
                pstmt.execute();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
