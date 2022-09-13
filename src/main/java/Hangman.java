import java.util.Scanner;

public class Hangman {
    static String[] bank = WordBank.words;
    static String currentGuess="";
    static String gameWord = "";
    static String hiddenWord;
    static int failedGuessCount = 0;
    static String[] gameBoord = {"[]========?\n","[]\n","[]\n","[]\n","[]\n","[]\n","[]\n","[]\n","[]========\n"};
    static String[] failBoard = {"[]========?\n","[]........|\n","[]........O\n","[]........|\n","[]......./|\n","[]......./|\\\n","[]........|\n","[]......./ \\\n","[]\n","[]\n","[]========\n"};
    public static void main(String[] args) {
        gameStart();
    }
    public static void gameStart() {
        gameIntro();
    };
    public static void gameIntro() {
        System.out.println("Welcome to hangman! \n\n" + "You have 8 guesses to guess the word I chose. Good luck!");
        computerChooses();
    }
    public static void computerChooses() {
        int index = (int) (Math.random() * 1000);
        gameWord = bank[index];
        setHiddenWord();
        displayGameBoard();
        displayWordPanel();
        playerGuesses();
    }

    public static void setHiddenWord() {
        hiddenWord = "_".repeat(gameWord.length());
    }
    public static void displayGameBoard() {
        String[] g = gameBoord;
        System.out.println(g[0] + g[1] + g[2] + g[3] + g[4] + g[5] + g[6] + g[7] + g[8]);
    }
    public static void displayWordPanel() {
        System.out.println("**************************");
        System.out.println(hiddenWord);
        System.out.println("**************************");
    }
    public static void playerGuesses() {
        Scanner entry = new Scanner(System.in);
        System.out.print("Do you want to guess a letter or the word (l/w): ");
        String response = entry.next().toLowerCase();
        String[] acceptable = {"l", "w", "letter", "word"};

        if(validate(acceptable, response)) {
            if(response.equals("l") || response.equals("letter")) guessLetter();
            else guessWord();
        } else {
            System.out.println("Invalid response!");
            playerGuesses();
        }
    }
    public static boolean validate(String[] acceptable, String given) {
        for (String s: acceptable){
            if(given.equals(s)) return true;
        }
        return false;
    }

    public static void guessLetter() {
        Scanner entry = new Scanner(System.in);
        System.out.print("Please guess a letter: ");
        String response = entry.next().toLowerCase();
        String[] acceptable = "abcdefghijklmnopqrstuvwxyz".split("");

        if(validate(acceptable, response)){
            currentGuess = response;
            if(checkGuess()) {
                updateHiddenWord();
            }
            else onWrongGuess();
        }else {
            System.out.println("Enter a valid guess");
            guessLetter();
        }
        displayGameBoard();
        displayWordPanel();
    }
    public static void guessWord() {
        Scanner entry = new Scanner(System.in);
        System.out.print("Please guess the word: ");
        String response = entry.next();
        currentGuess= response;
        if(checkGuess()) {
            gamePlayStatus();
        }
        else {
            onWrongGuess();
        }
//        displayGameBoard();
//        displayWordPanel();
    }
    public static boolean checkGuess() {
        if(currentGuess.length() > 1) {
            return gameWord.equals(currentGuess);
        }
        return gameWord.indexOf(currentGuess) > 0;
    }

    public static void updateHiddenWord() {
        if (gameWord.equals(currentGuess)) {
            gamePlayStatus();
        }
        else {
            String[] hidden = gameWord.split("");
            StringBuilder newHidden = new StringBuilder();

            for (String s : hidden) {
                if (s.equals(currentGuess)) {
                    newHidden.append(currentGuess);
                } else if(s.equals("_")) {
                    newHidden.append("_ ");
                }
            }
            hiddenWord = String.valueOf(newHidden);
        }
        displayGameBoard();
        displayWordPanel();
        gamePlayStatus();
    }
    public static void gamePlayStatus() {
        if(currentGuess.equals(gameWord)) gameWon();
        playerGuesses();
    }

    public static void onWrongGuess() {
        failedGuessCount++;
        System.out.println("Sorry there are no " + currentGuess + "'s in this word");
        displayGameBoard();
        displayWordPanel();
        if (failedGuessCount == 8) gameLost();
        else {
            updateDisplay();
            gamePlayStatus();
        }
    }
    public static void updateDisplay() {
        gameBoord[failedGuessCount] = failBoard[failedGuessCount];
    }

    public static void askReplay() {
        Scanner entry = new Scanner(System.in);
        System.out.print("Would you like to play again (y/n): ");
        String response = entry.next().toLowerCase();
        String[] acceptable = {"y","n"};

        if(validate(acceptable, response)){
            if(response.equals("y")) gameIntro();
            else endGame();
        }else {
            System.out.println("Enter a valid guess");
            askReplay();
        }
    }

    public static void gameWon() {
        System.out.println("Congratulations! You won the game!");
        askReplay();
    }
    public static void gameLost() {
        System.out.println("You lost the game, better luck next time");
        System.out.println("\nThe word you failed to guess was: " + gameWord);
        askReplay();
    }

    public static void endGame() {
        System.out.println("Thanks for playing!");
        resetGame();
    }
    public static void resetGame() {
        currentGuess="";
        gameWord = "";
        hiddenWord = "";
        failedGuessCount = 0;
        gameBoord = new String[]{"[]========?\n", "[]\n", "[]\n", "[]\n", "[]\n", "[]\n", "[]\n", "[]\n", "[]========\n"};
    }

//
//    public static void failedGuess() {
//        failedGuesses++;
//        display();
//    }

//    public static void rightGuess() {
//
//    }
//
//    public static boolean validate(String acceptable, String given) {
//        return given.length() == 1 && acceptable.indexOf(given) >= 0;
//    }
//
}