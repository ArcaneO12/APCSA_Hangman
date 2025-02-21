import java.util.Arrays;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        WordProvider wordProvider = new WordProvider();
        //String str = wordProvider.getWord();
        String str = "nolan";
        char[] word = str.toCharArray();
        char[] display = str.toCharArray();
        Arrays.fill(display, '_');

        System.out.println("*HANGMAN*\n");
        System.out.print("Player 1, input name: ");
        Player p0 = new Player(sc.nextLine());
        System.out.print("Player 2, input name: ");
        Player p1 = new Player(sc.nextLine());

        System.out.println("Your word is " + str.length() + " letters long.\n");
        System.out.println(display);
        
        for (int turn = 0; !Arrays.equals(word, display); turn++) {
            Player player = (turn % 2 == 0) ? p0 : p1;
            System.out.print(player.getName() + "\'s guess: ");
            String guess = sc.nextLine();
            if (guess.length() == 1) {
                char chGuess = guess.charAt(0);
                int times = 0;
                for (int i = 0; i < word.length; i++) {
                    if (chGuess == str.charAt(i)) {
                        display[i] = chGuess;
                        times++;
                        player.changeScore((int) (Math.random() * 100 * times));
                    }
                }
            } else if (guess.equals(str)) {
                display = word;
                p1.changeScore((int) (Math.random() * 40 * str.length()));
            }
            System.out.println(display);
        }

        System.out.println("\nThe word was \"" + str + "\"!");
        System.out.println(p0.getName() + "'s score: " + p0.getScore() + ".");
        System.out.println(p1.getName() + "'s score: " + p1.getScore() + ".");
        if (p0.getScore() > p1.getScore()) {
            System.out.println(p0.getName() + " wins!");
        } else {
            System.out.println(p1.getName() + " wins!");
        }

        // Main switches between players with the Player method, who guess with the
        // Guess method.
    }
}