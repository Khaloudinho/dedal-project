package fr.miage.m2.storage.persistkits.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class which implements a simple singleton in order to have one connection to our mysql database
 */
public final class EntityManager {

    static EntityManager entityManager;

    /**
     * Constructs one EntityManager instance,
     * no thread safe needed only one application and one user
     *
     * @return
     */
    public static EntityManager getInstance() {
        if (entityManager == null) {
            entityManager = new EntityManager();
        }
        return entityManager;
    }

    /**
     * Method which interacts with the postgres database in order a high score for a given user
     *
     * @param username concerned user
     * @return high score for the concerned user
     */
    public static Integer getUserHighScoreByUserName(String username) {
        Integer highScore = 0;
        try {
            PreparedStatement pstmt = PostgresConnection.getDbCon().conn.prepareStatement(
                    "SELECT score FROM highscores WHERE username = ?;");

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

    /**
     * Method which create or update a high score for a given user
     *
     * @param username         concerned user
     * @param currentHighScore score candidate for high score
     */
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
