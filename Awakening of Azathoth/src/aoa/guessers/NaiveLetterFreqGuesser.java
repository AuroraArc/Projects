package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.*;

public class NaiveLetterFreqGuesser implements Guesser {
    final List<String> words;

    public NaiveLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Makes a guess which ignores the given pattern. */
    public char getGuess(String pattern, List<Character> guesses) {
        return GuessHelper.getGuess(guesses, words);
    }

    public char getGuess(List<Character> guesses) {
        return GuessHelper.getGuess(guesses, words);
    }



    public static void main(String[] args) {
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("proj0/data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + GuessHelper.getFrequencyMap(nlfg.words));

        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + GuessHelper.getGuess(guesses, nlfg.words));
    }
}
