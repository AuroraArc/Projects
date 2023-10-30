package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.*;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        List<String> temp = new ArrayList<>();
        List<String> temp2 = new ArrayList<>();
        List<String> finalList;
        char[] patternArray = pattern.toCharArray();
        for (int i = 0; i < patternArray.length; i++) {
            if (patternArray[i] == '-') {
                continue;
            }
            if (temp.size() == 0) {
                for (String s : words) {
                    if (s.charAt(i) == patternArray[i] && s.length() == pattern.length()) {
                        temp.add(s);
                    }
                }
            }
            else {
                for (String s : temp) {
                    if (pattern.charAt(i) == s.charAt(i)) {
                        temp2.add(s);
                    }
                }
                temp = List.copyOf(temp2);
                temp2.clear();
            }
        }
        finalList = List.copyOf(temp);

        if (pattern.equals("----")) {
            finalList = words;
        }

        return GuessHelper.getGuess(guesses, finalList);
    }

    public static void main(String[] args) {
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        System.out.println(palfg.getGuess("-e--", List.of('e')));
    }
}