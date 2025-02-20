import java.util.List;

public class GameController implements IGameController {
    private IText user;
    private IDatabase db;
    private Game game;

    public GameController(IText user, IDatabase db, Game game) {
        this.user = user;
        this.db = db;
        this.game = game;
    }
    @Override
    public void run() {
        boolean playAgain = true;
        user.addString("Enter user name:\n ");
        String name=user.getString();
        int id= db.getPlayerId(name);

        try {
            if(id<=0){
                System.out.println("User not found in database please register with admin");
                Thread.sleep(5000);
                user.exit();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        while(playAgain){
            playRound(id);
            playAgain= user.YesNo("Correct,Continue");
        }
        user.exit();

    }

    private void playRound(int playerId) {
        String goal= game.makeGoal();
        user.clear();

        user.addString("New Game:\n");
        user.addString("For practice, number is: " + goal + "\n");
        //user.addString("For practice, alphabet is: " + goal + "\n");
        String guess= user.getString();
        int attempts=1;

        String feedBack= game.generateFeedback(goal,guess);
        user.addString(feedBack +" \n");

        while(!feedBack.equals("BBBB,")){
            attempts++;
            guess= user.getString();
            feedBack= game.generateFeedback(goal,guess);
            user.addString(feedBack +" \n");
        }
        db.saveResult(playerId,attempts);
        showTopTen();

    }

    private void showTopTen() {
        List<PlayerAverage> topList=db.getListofTopTenPlayers();
        user.addString("Top Ten:\n  Player  Average\n");
        int position=0;
        for(PlayerAverage p:topList){
            user.addString(String.format("%3d %-10s%5.2f%n", position, p.getName(), p.getAverage()));
            if(position++==10) break;
        }
    }


}
