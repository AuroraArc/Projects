package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

import java.util.List;

public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern = "";

    final List<String> words;

    public RandomChooser(int wordLength, String dictionaryFile) {
        words = FileUtils.readWordsOfLength(dictionaryFile, wordLength);

        if (wordLength < 1) {
            throw new IllegalArgumentException("The word should be longer than one character.");
        }
        if (words.size() == 0) {
            throw new IllegalStateException("There are no words of that length.");
        }

        int numWords = words.size();
        int randomlyChosenWordNumber = StdRandom.uniform(numWords);
        chosenWord = words.get(randomlyChosenWordNumber);

        for (int i = 0; i < wordLength; i++) {
            pattern += '-';
        }
    }

    @Override
    public int makeGuess(char letter) {
        int count = 0;
        char[] word = chosenWord.toCharArray();
        char[] patternArray = pattern.toCharArray();
        for (int i = 0; i < chosenWord.length(); i++) {
            if (chosenWord.charAt(i) == letter) {
                count++;
                patternArray[i] = letter;
            }
        }
        pattern = String.valueOf(patternArray);
        return count;
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return chosenWord;
    }
}
