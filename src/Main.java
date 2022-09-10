import javax.net.ssl.SNIHostName;
import java.util.Scanner;

public class Main {
    static String[] bank = WordBank.words;
    static char currentGuess;
    static String selectedWord = "";
    static int failedGuesses = 0;
    static String[] d = {"[]========\n","[]\n","[]\n","[]\n","[]\n","[]\n","[]\n","[]\n","[]========\n"};
    static String[] d1 = {"[]========?\n","[]........|\n","[]........O\n","[]........|\n","[]......./|\n","[]......./|\\\n","[]........|\n","[]......./ \\\n","[]\n","[]\n","[]========\n"};
    public static void main(String[] args) {
        System.out.println("Hello world!");
        gameStart();
    }
    public static void gameStart() {
        System.out.println("Let's Play!");
        computerChooses();
    };
    public static void computerChooses() {
        int index = (int) (Math.random() * 1000);
        String computerChoice = bank[index];
        selectedWord = computerChoice;
        display();
        playerGuesses();
    }

    public static void display() {
        d[failedGuesses] = d1[failedGuesses];
        System.out.println(d[0]+d[1]+d[2]+d[3]+d[4]+d[5]+d[6]+d[7]+d[8]);
//        System.out.println(d1[0]+d1[1]+d1[2]+d1[3]+d1[4]+d1[5]+d1[6]+d1[7]+d1[8]);
        printer();
    }

    public static void printer() {
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < selectedWord.length(); i++) {
            word.append("_.");
        }
        System.out.println(word);

    }

    public static void failedGuess() {
        failedGuesses++;
        display();
    }
    public static boolean checkGuess() {
        return selectedWord.indexOf(currentGuess) > 0;
    }
    public static void rightGuess() {

    }
    public static void playerGuesses() {
        Scanner entry = new Scanner(System.in);
        System.out.print("Please enter your guess: ");
        String response = entry.next().toLowerCase();
        String acceptable = "abcdefghijklmnopqrstuvwxyz";

        if(validate(acceptable, response)){
            currentGuess = response.charAt(0);
            if(checkGuess())rightGuess();
            else failedGuess();
        }else {
            System.out.println("Enter a valid guess");
            playerGuesses();
        }
    }
    public static boolean validate(String acceptable, String given) {
        return given.length() == 1 && acceptable.indexOf(given) >= 0;
    }

}