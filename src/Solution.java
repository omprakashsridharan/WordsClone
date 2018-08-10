import java.io.IOException;
import java.util.Scanner;

public class Solution {
    private final static int EASY = 4;
    private final static int MEDIUM = 5;
    private final static int HARD = 6;
    public static void main(String[] args) throws IOException {
        int wordLength = getDifficultyLevel();
        Game g = new Game(wordLength);
        g.play();
    }

    private static int getDifficultyLevel() {
        int level = 1;
        System.out.println("Enter the difficulty level\n1. EASY("+EASY+" Letters)\n2. MEDIUM("+MEDIUM+" Letters)\n3. HARD("+HARD+" Letters)");
        Scanner scanner = new Scanner(System.in);
        level = scanner.nextInt();
        switch (level){
            case 1:
                return EASY;
            case 2:
                return MEDIUM;
            case 3:
                return HARD;
            default:
                return EASY;
                    
        }
    }
}
