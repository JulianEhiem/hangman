import java.util.ArrayList;
import java.util.Scanner;

public class Hangman {
    static String[] wordBank = WordBank.words;
    static String gameWord = computerChoice(wordBank);
    static String hiddenWord = maskWord(gameWord);
    static ArrayList<String> gameBoard = createGameBoard();
    static ArrayList<String> pastGuesses = new ArrayList<>();
    static boolean gameWon = false;
    static boolean gameLost = false;
    static boolean continueGame = true;

    public static void main(String[] args) {
        gameStart();
    }

    public static void gameStart() {
        System.out.println("Welcome to hangman! \n\n" + "You have 8 guesses to guess the word I chose. Good luck!");
        int failedGuesses = 0;
        while (continueGame) {
            gameDisplay(gameBoard, hiddenWord, failedGuesses);
            String playerGuess = playerChooses();
            if (isGuessCorrect(playerGuess, gameWord)) {
                hiddenWord = updatedHiddenWord(gameWord, hiddenWord, playerGuess);
                gameWon = gameWord.equals(playerGuess) || hiddenWord.equals(gameWord);
            } else {
                failedGuesses++;
                gameLost = failedGuesses == 8;
                gameBoard = updatedGameBoard(failedGuesses);
            }
            pastGuesses.add(playerGuess);
            if (gameLost || gameWon) continueGame = false;
        }
        ;

        if (gameWon) gameWonDialog();
        if (gameLost) gameLostDialog();
        System.out.println("\nGame ended!");
    }

    private static void gameLostDialog() {
        System.out.println("\nYou did not guess the word \"" + gameWord + "\"\n");
        System.out.print("\n============ " + "\nYOU LOST" + "\n============ ");
    }

    private static void gameWonDialog() {
        System.out.println("\nYou guessed \"" + gameWord + "\" correctly!\n");
        System.out.print("\n================ " + "\nCONGRATULATIONS!!!" + "\n================ ");
    }

    private static void gameDisplay(ArrayList<String> gameBoard, String hiddenWord, int failedGuesses) {
        StringBuilder boardString = new StringBuilder();
        for (String s : gameBoard) {
            boardString.append(s);
        }
        System.out.println(boardString);
        if (pastGuesses.size() != 0) {
            System.out.print("Previous guesses: ");
            for (String pastGuess : pastGuesses) {
                System.out.print(pastGuess + ", ");
            }
        }
        System.out.println("\nYou have " + (8 - failedGuesses) + " wrong guesses left.");
        System.out.print("\n===============" + "\n" + hiddenWord + "\t <- (" + hiddenWord.length() + " letter word)" + "\n===============\n");
    }

    public static ArrayList<String> createGameBoard() {
        ArrayList<String> board = new ArrayList<>();
        board.add("[]===========?\n");
        board.add("[]\n");
        board.add("[]\n");
        board.add("[]\n");
        board.add("[]\n");
        board.add("[]\n");
        board.add("[]\n");
        board.add("[]\n");
        board.add("[]============\n");
        return board;
    }

    public static ArrayList<String> updatedGameBoard(int failCount) {
        ArrayList<String> newGameBoard = gameBoard;
        switch (failCount) {
            case 0:
                return createGameBoard();
            case 1:
                newGameBoard.set(1, "[]" + "\t\t\t |\n");
                break;
            case 2:
                newGameBoard.set(2, "[]" + "\t\t\t O\n");
                break;
            case 3:
                newGameBoard.set(3, "[]" + "\t\t\t |\n");
                break;
            case 4:
                newGameBoard.set(3, "[]" + "\t\t\t/|\n");
                break;
            case 5:
                newGameBoard.set(3, "[]" + "\t\t\t/|\\\n");
                break;
            case 6:
                newGameBoard.set(4, "[]" + "\t\t\t |\n");
                break;
            case 7:
                newGameBoard.set(5, "[]" + "\t\t\t/ \n");
                break;
            case 8:
                newGameBoard.set(5, "[]" + "\t\t\t/ \\\n");
                break;
            default:
                return gameBoard;
        }
        return newGameBoard;
    }

    public static String computerChoice(String[] bank) {
        if (bank.length == 0) {
            throw new IllegalArgumentException("Array length cannot be zero");
        }
        int index = (int) (Math.random() * bank.length);
        return bank[index];
    }

    public static String maskWord(String word) {
        return "_".repeat(word.length());
    }

    public static String playerChooses() {
        Scanner entry = new Scanner(System.in);
        System.out.print("\nDo you want to guess a letter or the word (l/w): ");
        String response = entry.next().toLowerCase();
        String[] acceptable = {"l", "w", "letter", "word"};
        String guess = "";

        if (validate(acceptable, response)) {
            if (response.equals("l") || response.equals("letter")) {
                guess = guessLetter();
            } else {
                guess = guessWord();
            }
        } else {
            System.out.println("Invalid response!");
            playerChooses();
        }
        return guess;
    }

    public static boolean validate(String[] acceptable, String given) {
        for (String s : acceptable) {
            if (given.equals(s)) return true;
        }
        return false;
    }

    public static String guessLetter() {
        Scanner entry = new Scanner(System.in);
        System.out.print("Please guess a letter: ");
        String response = entry.next().toLowerCase();
        String[] acceptable = "abcdefghijklmnopqrstuvwxyz".split("");

        if (validate(acceptable, response)) {
            return response;
        } else {
            System.out.println("Enter a valid guess");
            guessLetter();
        }
        return response;
    }

    public static String guessWord() {
        Scanner entry = new Scanner(System.in);
        System.out.print("Please guess the word: ");
        String response = entry.next();
        return response;
    }

    public static boolean isGuessCorrect(String guess, String word) {
        if (guess.length() != 1) {
            return word.equals(guess);
        } else {
            return word.contains(guess);
        }
    }

    public static String updatedHiddenWord(String gameWord, String hiddenWord, String playerGuess) {
        if (gameWord.equals(playerGuess)) {
            return gameWord;
        } else {
            String[] word = gameWord.split("");
            StringBuilder newHidden = new StringBuilder();

            for (int i = 0; i < word.length; i++) {
                String w = word[i];
                String h = String.valueOf(hiddenWord.charAt(i));
                if (w.equals(playerGuess)) {
                    newHidden.append(playerGuess);
                } else {
                    if (!h.equals("_")) {
                        newHidden.append(h);
                    } else {
                        newHidden.append("_");
                    }
                }
            }
            return String.valueOf(newHidden);
        }
    }
}