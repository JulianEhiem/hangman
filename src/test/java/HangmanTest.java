import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HangmanTest {
//    private final Hangman hangman = new Hangman();

    @Test
    @DisplayName("Computer word bank should not be empty")
    void arrayShouldNotBeEmpty() {
        var test = new String[]{};
        assertThrows(IllegalArgumentException.class,  () -> {
            Hangman.computerChoice(test);
        });
    }

    @Test
    @DisplayName("Guess should be true if it matches all or a letter of the word")
    void guessShouldBeTrueIfItMatchesALetterOrAllOfWord(){
        assertAll(
                () ->  assertTrue(Hangman.isGuessCorrect("guess", "guess")),
                () ->  assertFalse(Hangman.isGuessCorrect("guesses", "guess")),
                () ->  assertFalse(Hangman.isGuessCorrect("ess", "guess")),
                () ->  assertTrue(Hangman.isGuessCorrect("g", "guess"))
        );
    }

    @Test
    @DisplayName("Hidden word should be updated on correct guess")
    void hiddenWordShouldBeUpdatedOnCorrectGuess(){
        assertAll(
                () -> assertEquals("dancing",Hangman.updatedHiddenWord("dancing", "dancing")),
                () -> assertEquals("__ss_ss____",Hangman.updatedHiddenWord("mississippi", "s"))
        );
    }

    @Test
    @DisplayName("The gameboard is created")
    void createGameBoard(){
        final List<String> result = Hangman.createGameBoard();
        assertAll(
                () -> assertNotNull(result, "List should not be null"),
                () -> assertEquals(9, result.size())
        );
    }

    @Test
    @DisplayName("The gameboard is updated")
    void updateGameBoard(){
        String[] board = {"0","0","0","0","0","0","0","0"};
//        assertEquals(new String[]{"0", "0", "0", "0", "0", "5", "0", "0"}, Hangman.updatedGameBoard(board, 1));
    }

}