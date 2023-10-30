package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.*;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        List<String> temp = new ArrayList<>();
        List<String> temp2 = new ArrayList<>();
        char[] patternArray = pattern.toCharArray();
        boolean allDash = false;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) != '-') {
                allDash = false;
                break;
            }
            allDash = true;
        }
        if (guesses.size() != 0 && allDash) {
            for (int j = 0; j < words.size(); j++) {
                boolean found = false;
                for (int i = 0; i < words.get(j).length(); i++) {
                    for (char c : guesses) {
                        if (words.get(j).charAt(i) == c) {
                            words.remove(j);
                            found = true;
                            j--;
                            break;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < patternArray.length; i++) {
            if (patternArray[i] == '-') {
                continue;
            }
            if (temp.size() == 0) {
                for (String s : words) {
                    if (s.length() == pattern.length() && s.charAt(i) == patternArray[i]) {
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

        List<String> finalList = new ArrayList<>(temp);

        for (int k = 0; k < finalList.size(); k++) {
            remove:
            {
                String test = finalList.get(k);
                char[] stringToChar = test.toCharArray();
                List<Character> patternChar = new ArrayList<>();
                for (int i = 0; i < patternArray.length; i++) { // adds all characters in the pattern to a char array
                    if (patternArray[i] != '-') {
                        patternChar.add(patternArray[i]);
                        stringToChar[i] = '-';
                    }
                }
                test = String.valueOf(stringToChar);

                for (int i = 0; i < patternChar.size(); i++) { // remove duplicates from the patternChar array
                    for (int j = i + 1; j < patternChar.size(); j++) {
                        if (patternChar.get(i) == patternChar.get(j)) {
                            patternChar.remove(i);
                            i--;
                            break;
                        }
                    }
                }

                for (int i = 0; i < test.length(); i++) { // if there is another appearance of a pattern letter in the wrong place, remove word
                    for (char c : patternChar) {
                        if (test.charAt(i) == c) {
                            finalList.remove(k);
                            k--;
                            break remove;
                        }
                    }
                }
            }
        }

        List<Character> noGuess = new ArrayList<>(guesses);
        for (int i = 0; i < noGuess.size(); i++) {
            for (char cc : patternArray) {
                if (noGuess.get(i) == cc) {
                    noGuess.remove(i);
                    i--;
                    break;
                }
            }
        }

        for (int j = 0; j < finalList.size() && noGuess.size() != 0; j++) { // removes any word that contains a guess that is not found in the pattern
            for (int i = 0; i < finalList.get(j).length(); i++) {
                boolean found = false;
                for (char c : noGuess) {
                    if (c == finalList.get(j).charAt(i)) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    finalList.remove(j);
                    j--;
                    break;
                }
            }
        }


        if (allDash) {
            finalList = words;
        }

        return GuessHelper.getGuess(guesses, finalList);
    }



    public static void main(String[] args) {
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("skeleton/proj0/data/example.txt");
        System.out.println(pagalfg.getGuess("----", List.of('e')));
    }
}
