
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class DatabaseImpl implements Database {
    private Connection connection;
    private Statement statement;

    public DatabaseImpl()  {
        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/MOO","root","vedansh143@P");
            statement=connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getPlayerId(String name)  {
        try {
            ResultSet rs=statement.executeQuery("SELECT id FROM players WHERE name = '" + name + "'");
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public void saveResult(int playerId, int attempts)  {
        try {
            statement.executeUpdate("INSERT INTO results (result, playerId) VALUES (" + attempts + ", " + playerId + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PlayerAverage> getListofTopTenPlayers() {
        List<PlayerAverage> topList = null;
        try {
            topList = new ArrayList<>();
            Statement stmt2 = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM players");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                ResultSet rs2 = stmt2.executeQuery("SELECT * FROM results WHERE playerid = " + id);

                int nGames = 0, totalGuesses = 0;
                while (rs2.next()) {
                    nGames++;
                    totalGuesses += rs2.getInt("result");
                }
                if (nGames > 0) {
                    topList.add(new PlayerAverage(name, (double) totalGuesses / nGames));
                }
            }
            topList.sort(Comparator.comparingDouble(p -> p.getAverage()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return topList;
    }


}
