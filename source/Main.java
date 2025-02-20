

public class Main {
    public static void main(String[] args)  {
        GameController game = new GameController(new SimpleWindowUI(), new Database(), new NumberGameLogic());
            game.run();

    }
}