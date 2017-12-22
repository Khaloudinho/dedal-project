package fr.miage.m2.storage.persistkits.jdbc;

import java.sql.*;

/**
 * Class which manages PostgresConnection
 */
public final class PostgresConnection {

    public static PostgresConnection db;
    public Connection conn;
    private Statement statement;

    /**
     * Constructs PostgresConnection
     */
    private PostgresConnection() {
        String url = "jdbc:postgresql://92.222.86.67:5432/";
        //String url= "jdbc:postgresql://127.0.0.1:5432/";
        String dbName = "dedal";
        String driver = "org.postgresql.Driver";
        String userName = "postgres";
        //String password = "rootroot";
        String password = "toJIN";
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection) DriverManager.getConnection(url + dbName, userName, password);
        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * @return PostgresConnexion BDD connection object
     */
    public static synchronized PostgresConnection getDbCon() {
        if (db == null) {
            db = new PostgresConnection();
        }
        return db;
    }

    /**
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not available
     * @throws SQLException
     */
    public ResultSet query(String query) throws SQLException {
        statement = db.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }

    /**
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     * @desc Method to insert data to a table
     */
    public int insert(String insertQuery) throws SQLException {
        statement = db.conn.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;
    }
}

