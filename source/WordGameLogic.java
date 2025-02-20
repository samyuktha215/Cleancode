import java.util.Random;

public class WordGameLogic implements Game{
    @Override
    public String makeGoal() {
        Random rand = new Random();
        StringBuilder goal = new StringBuilder();
        String alphabet= "abcdefghijklmnopqrstuvwxyz";
        while (goal.length() < 4) {
            char randomChar = alphabet.charAt(rand.nextInt(alphabet.length()));

            if (goal.indexOf(String.valueOf(randomChar)) == -1) {
                goal.append(randomChar);
            }
        }

        return goal.toString();

    }

    @Override
    public String generateFeedback(String goal, String guess) {
        if (guess.length() != 4) return "Invalid guess! Must be 4 letters.";

        int cows = 0, bulls = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (goal.charAt(i) == guess.charAt(j)) {
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }

        return "B".repeat(bulls) + "," + "C".repeat(cows);
    }

}
