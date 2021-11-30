package transit_connection;

import java.sql.*;

public class InsertValues {
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insertIntoLineID(int ID, String LineName,String StaringStop) {
        String sql = "INSERT INTO LineID(ID, LineName, StaringStop) VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ID);
            pstmt.setString(2, LineName);
            pstmt.setString(3, StaringStop);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertIntoStartingTimes(int LineID, String StartingTime) {
        String sql = "INSERT INTO StartingTimes(LineID,StartingTime) VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, LineID);
            pstmt.setString(2, StartingTime);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertIntoStopIds(int ID, String StopName) {
        String sql = "INSERT INTO StopIds(ID,StopName) VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ID);
            pstmt.setString(2, StopName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertIntoPaths(int LineID, int StopFromID, int StopToID, int Duration) {
        String sql = "INSERT INTO Paths(LineID, StopFromID, StopToID, Duration) VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, LineID);
            pstmt.setInt(2, StopFromID);
            pstmt.setInt(3, StopToID);
            pstmt.setInt(4, Duration);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertIntoNumberOfPassengers(int LineID, int From, int To, int startingTime, int numberOfPassengers) {
        String sql = "INSERT INTO NumberOfPassengers(LineID, From, To, startingTime, numberOfPassengers) VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, LineID);
            pstmt.setInt(2, From);
            pstmt.setInt(3, To);
            pstmt.setInt(4, startingTime);
            pstmt.setInt(5, numberOfPassengers);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
