import java.util.Arrays;

public class Display {
    String word;
    private char[] display;

    public Display(String word) {
        this.word = word;
        display = word.toCharArray();
        Arrays.fill(display, '_');
    }

    public String getDisplayStr() {
        return new String(display);
    }

    public void revealDisplay() {
        display = word.toCharArray();
    }

    public int changeDisplay(char guess) {
        int times = 0;
        for (int i = 0; i < word.length(); i++) {
            if (guess == word.charAt(i) && display[i] == '_') {
                display[i] = guess;
                times++;
            }
        }
        return times;
    }

    public boolean isSolved() {
        return getDisplayStr().equals(word);
    }
}