package aoa.guessers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GuessHelper {

    /** Returns a map from a given letter to its frequency across all words.
     *  This task is similar to something you did in hw0b! */
    public static Map<Character, Integer> getFrequencyMap(List<String> words) {
        Map<Character, Integer> map = new TreeMap<>();
        for (String s : words) {
            for (int i = 0; i < s.length(); i++) {
                if (map.containsKey(s.charAt(i))) {
                    map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
                }
                else {
                    map.put(s.charAt(i), 1);
                }
            }
        }
        return map;
    }

    /** Returns the most common letter in WORDS that has not yet been guessed
     *  (and therefore isn't present in GUESSES). */
    public static char getGuess(List<Character> guesses, List<String> words) {
        Map<Character, Integer> map = getFrequencyMap(words);
        if (map.isEmpty()) { // checks if there are any keys in the frequency map
            return '?';
        }
        for (char c : guesses) { // removes all the characters that have been guessed from the frequency map
            map.remove(c);
        }


        List<Character> keyArray = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            keyArray.add((Character)map.keySet().toArray()[i]); // converts the keys in the map into an array
        }
        List<Character> duplicates = new ArrayList<>();
        int answer = map.get(keyArray.get(0)); // looks for the key that has the highest value
        duplicates.add(keyArray.get(0));
        for (int i = 1; i < keyArray.size(); i++) {
            if (map.get(keyArray.get(i)) == answer) {
                duplicates.add(keyArray.get(i));
            }
            if (map.get(keyArray.get(i)) > answer) {
                duplicates.clear();
                answer = map.get(keyArray.get(i));
                duplicates.add(keyArray.get(i));
            }
        }
        return duplicates.get(0); // since it is a TreeMap, it is already sorted in alphabetical order
    }

}
