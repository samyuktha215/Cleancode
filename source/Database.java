import java.util.List;

public interface Database {
    int getPlayerId(String playerName) ;
    void saveResult(int playerid,int attempts) ;
    List<PlayerAverage> getListofTopTenPlayers();
}
