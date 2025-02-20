import java.util.List;

public interface IDatabase {
    int getPlayerId(String playerName) ;
    void saveResult(int playerid,int attempts) ;
    List<PlayerAverage> getListofTopTenPlayers();
}
