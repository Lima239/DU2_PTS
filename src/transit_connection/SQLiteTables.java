package transit_connection;

import java.sql.*;

public class SQLiteTables {
    public static void main( String args[] ) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "DROP TABLE  IF EXISTS LineIDS;" +
                    "DROP TABLE  IF EXISTS StartingTimes;" +
                    "DROP TABLE  IF EXISTS StopIds;" +
                    "DROP TABLE  IF EXISTS Paths;" +
                    "DROP TABLE  IF EXISTS NumberOfPassengers;";

            sql += "CREATE TABLE LineIDS " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " LineName           TEXT    NOT NULL, " +
                    " StartingStop         TEXT    NOT NULL)";

            sql += "CREATE TABLE StartingTimes " +
                    "(LineID IS NULL OR EXISTS(SELECT 1 FROM LineIDS WHERE ID=LineID)," +
                    " StartingTime          TEXT    NOT NULL) " ;

            sql += "CREATE TABLE StopIds " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " StopName           TEXT    NOT NULL)";

            sql += "CREATE TABLE Paths " +
                    "(LineID IS NULL OR EXISTS(SELECT 1 FROM LineIDS WHERE ID=LineID)," +
                    " StopFromID           INT    NOT NULL, " +
                    " StopToID            INT     NOT NULL, " +
                    " Duration         INT)";

            sql += "CREATE TABLE NumberOfPassengers " +
                    "(LineID IS NULL OR EXISTS(SELECT 1 FROM LineIDS WHERE ID=LineID)," +
                    " From IS NULL OR EXISTS(SELECT 1 FROM Paths WHERE StopFromID=From), " +
                    " To IS NULL OR EXISTS(SELECT 1 FROM Paths WHERE StopToID=To), " +
                    " startingTime      INT ," +
                    " numberOfPassengers        INT)";

            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}
