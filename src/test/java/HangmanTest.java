import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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
//                () ->  assertTrue(Hangman.isGuessCorrect("guess", "guess")),
//                () ->  assertFalse(Hangman.isGuessCorrect("guess", "gu")),
//                () ->  assertFalse(Hangman.isGuessCorrect("guess", "ess")),
                () ->  assertTrue(Hangman.isGuessCorrect("guess", "g"))
        );
    }

    @Test
    @DisplayName("Hidden word should be updated on correct guess")
    void hiddenWordShouldBeUpdatedOnCorrectGuess(){
        assertAll(
                () -> assertEquals("__k_",Hangman.updatedHiddenWord("cake", "k")),
                () -> assertEquals("_oo___",Hangman.updatedHiddenWord("cookie", "o")),
                () -> assertEquals("___g__g",Hangman.updatedHiddenWord("singing", "g")),
                () -> assertEquals("dancing",Hangman.updatedHiddenWord("dancing", "dancing")),
                () -> assertEquals("__ss_ss____",Hangman.updatedHiddenWord("mississippi", "s"))

        );
    }

}