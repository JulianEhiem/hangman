import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Hangman {
    static String[] wordBank = WordBank.words;
//    static String currentGuess="";
//    static String gameWord = "";
//    static String hiddenWord;
//    static int failedGuessCount = 0;
//    static String[] gameBoord = {"[]========?\n","[]\n","[]\n","[]\n","[]\n","[]\n","[]\n","[]\n","[]========\n"};
//    static String[] failBoard = {"[]========?\n","[]........|\n","[]........O\n","[]........|\n","[]......./|\n","[]......./|\\\n","[]........|\n","[]......./ \\\n","[]\n","[]\n","[]========\n"};
    public static void main(String[] args) {
        gameStart();
    }
    public static void gameStart() {
        System.out.println("Welcome to hangman! \n\n" + "You have 8 guesses to guess the word I chose. Good luck!");
        String gameWord = computerChoice(wordBank);
        String hiddenWord = maskWord(gameWord);
        int failedGuessesLeft = 8;
        boolean continueGame = true;
        do {
            String playerGuess = playerChooses();
            if (isGuessCorrect(playerGuess, gameWord)){
                hiddenWord = updatedHiddenWord(gameWord, playerGuess);
            }else {
                System.out.println("test");
//                updatedGameBoard();
//                update failed guess count
            }
//            display game board
//            continueGame = checkGameStatus();
        }while (continueGame);


//        setHiddenWord();
    };
//    public static void gameIntro() {
//        System.out.println("Welcome to hangman! \n\n" + "You have 8 guesses to guess the word I chose. Good luck!");
//        computerChooses();
//    }
    public static String computerChoice(String[] bank) {
        if (bank.length == 0) {
            throw new IllegalArgumentException("Array length cannot be zero");
        }
        int index = (int) (Math.random() * bank.length);
        return bank[index];
//        displayGameBoard();
//        displayWordPanel();
//        playerGuesses();
    }
    public static String maskWord(String word) {
        return "_".repeat(word.length());
    }
//    public static void displayGameBoard() {
//        String[] g = gameBoord;
//        System.out.println(g[0] + g[1] + g[2] + g[3] + g[4] + g[5] + g[6] + g[7] + g[8]);
//    }
//    public static void displayWordPanel() {
//        System.out.println("**************************");
//        System.out.println(hiddenWord);
//        System.out.println("**************************");
//    }
    public static String playerChooses() {
        Scanner entry = new Scanner(System.in);
        System.out.print("Do you want to guess a letter or the word (l/w): ");
        String response = entry.next().toLowerCase();
        String[] acceptable = {"l", "w", "letter", "word"};
        String guess = "";

        if(validate(acceptable, response)) {
            if(response.equals("l") || response.equals("letter")) {
                guess = guessLetter();
            }
            else {
                guess = guessWord();
            }
        } else {
            System.out.println("Invalid response!");
            playerChooses();
        }
        return guess;
    }
    public static boolean validate(String[] acceptable, String given) {
        for (String s: acceptable){
            if(given.equals(s)) return true;
        }
        return false;
    }
    public static String guessLetter() {
        Scanner entry = new Scanner(System.in);
        System.out.print("Please guess a letter: ");
        String response = entry.next().toLowerCase();
        String[] acceptable = "abcdefghijklmnopqrstuvwxyz".split("");

        if(validate(acceptable, response)){
//            currentGuess = response;
//            if(checkGuess()) {
//                updateHiddenWord();
//            }
//            else onWrongGuess();
            return response;
        }else {
            System.out.println("Enter a valid guess");
            guessLetter();
        }
//        displayGameBoard();
//        displayWordPanel();
        return response;
    }
    public static String guessWord() {
        Scanner entry = new Scanner(System.in);
        System.out.print("Please guess the word: ");
        String response = entry.next();
        return response;
//        currentGuess= response;
//        if(checkGuess()) {
//            gamePlayStatus();
//        }
//        else {
//            onWrongGuess();
//        }
//        displayGameBoard();
//        displayWordPanel();
    }
    public static boolean isGuessCorrect(String guess, String word) {
        if(guess.length() != 1) {
            return word.equals(guess);
        } else {
            return word.indexOf(guess) >= 0;
        }
    }
    public static String updatedHiddenWord(String gameWord, String playerGuess) {
        if (gameWord.equals(playerGuess)) {
            return gameWord;
        }
        else {
            String[] word = gameWord.split("");
            StringBuilder newHidden = new StringBuilder();

            for (String s : word) {
                if (s.equals(playerGuess)) {
                    newHidden.append(playerGuess);
                } else if(s.equals("_")) {
                    newHidden.append("_");
                }
            }
            return String.valueOf(newHidden);
        }
    }
//    public static void gamePlayStatus() {
//        if(currentGuess.equals(gameWord)) gameWon();
//        playerGuesses();
//    }
//    public static void onWrongGuess() {
//        failedGuessCount++;
//        System.out.println("Sorry there are no " + currentGuess + "'s in this word");
//        displayGameBoard();
//        displayWordPanel();
//        if (failedGuessCount == 8) gameLost();
//        else {
//            updateDisplay();
//            gamePlayStatus();
//        }
//    }
//    public static void updateDisplay() {
//        gameBoord[failedGuessCount] = failBoard[failedGuessCount];
//    }
//    public static void askReplay() {
//        Scanner entry = new Scanner(System.in);
//        System.out.print("Would you like to play again (y/n): ");
//        String response = entry.next().toLowerCase();
//        String[] acceptable = {"y","n"};
//
////        if(validate(acceptable, response)){
////            if(response.equals("y")) gameIntro();
////            else endGame();
////        }else {
////            System.out.println("Enter a valid guess");
////            askReplay();
////        }
//    }
//    public static void gameWon() {
//        System.out.println("Congratulations! You won the game!");
//        askReplay();
//    }
//    public static void gameLost() {
//        System.out.println("You lost the game, better luck next time");
//        System.out.println("\nThe word you failed to guess was: " + gameWord);
//        askReplay();
//    }
//    public static void endGame() {
//        System.out.println("Thanks for playing!");
//        resetGame();
//    }
//    public static void resetGame() {
//        currentGuess="";
//        gameWord = "";
//        hiddenWord = "";
//        failedGuessCount = 0;
//        gameBoord = new String[]{"[]========?\n", "[]\n", "[]\n", "[]\n", "[]\n", "[]\n", "[]\n", "[]\n", "[]========\n"};
//    }

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