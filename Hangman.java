import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        WordProvider wordProvider = new WordProvider();
        String word = wordProvider.getWord();
        Display display = new Display(word);

        System.out.println("*HANGMAN*\n");
        System.out.print("Player 1, input name: ");
        Player p0 = new Player(sc.nextLine());
        System.out.print("Player 2, input name: ");
        Player p1 = new Player(sc.nextLine());

        System.out.println("Your word is " + word.length() + " letters long.\n");
        System.out.println(display.getDisplayStr());
        
        for (int turn = 0; !display.isSolved(); turn++) {
            Player player = (turn % 2 == 0) ? p0 : p1;
            System.out.print(player.getName() + "\'s guess: ");
            String guess = sc.nextLine().toLowerCase();
            int change = 0;
            if (guess.length() == 1) {
                char chGuess = guess.charAt(0);
                int times = display.changeDisplay(chGuess);
                change = (int) ((Math.random() + 9) * 10 * times);
                player.changeScore(change);
            } else if (guess.equals(word)) {
                display.revealDisplay();
                change = (int) ((Math.random() + 9) * 4 * word.length());
                p1.changeScore(change);
            }
            System.out.println(display.getDisplayStr() + " (" + player.getName() + " gained " + change + " points!)");
        }

        System.out.println("\nThe word was \"" + word + "\"!");
        System.out.println(p0.getName() + "'s score: " + p0.getScore() + ".");
        System.out.println(p1.getName() + "'s score: " + p1.getScore() + ".");
        if (p0.getScore() > p1.getScore()) {
            System.out.println(p0.getName() + " won!");
        } else if (p0.getScore() < p1.getScore()) {
            System.out.println(p1.getName() + " won!");
        } else {
            System.out.println(p0.getName() + " and " + p1.getName() + " tied!");
        }
        sc.close();
    }
}