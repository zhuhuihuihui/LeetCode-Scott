import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Scott on 22/09/2016.
 */
public class Graph {















































    /**
     * 269. Alien Dictionary
     * Graph, Hard
     * https://leetcode.com/problems/alien-dictionary/
     * */
    public String alienOrder(String[] words) {
        // HashMap to maintain the map relationship between letters
        HashMap<Character, HashSet<Character>> hashMap = new HashMap<Character, HashSet<Character>>();
        // Store the indegree of each letter
        HashMap<Character, Integer> degree = new HashMap<Character, Integer>();
        StringBuilder ret = new StringBuilder();
        if (words == null || words.length < 1) return ret.toString();

        // Init indegree for every letter
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                degree.put(word.charAt(j), 0);
            }
        }

        // Build graph
        for (int i = 0; i < words.length - 1; i++) {
            String currentWord = words[i];
            String nextWord = words[i + 1];
            int diffCharIndex = 0;
            int minLength = Math.min(currentWord.length(), nextWord.length());
            while (diffCharIndex < minLength && currentWord.charAt(diffCharIndex) == nextWord.charAt(diffCharIndex))
                diffCharIndex++;
            if (diffCharIndex < minLength) {
                Character currentChar = currentWord.charAt(diffCharIndex);
                Character nextChar = nextWord.charAt(diffCharIndex);
                // Add next letter to the map of current letter
                HashSet<Character> setForCurrentLetter = hashMap.get(currentChar);
                if (setForCurrentLetter == null) {
                    setForCurrentLetter = new HashSet<Character>();
                }
                if (!setForCurrentLetter.contains(nextChar)) {
                    setForCurrentLetter.add(nextChar);
                    hashMap.put(currentChar, setForCurrentLetter);
                    // Add 1 to next letter's indegree
                    degree.put(nextChar, degree.get(nextChar) + 1);
                }
            }
        }

        Queue<Character> queue = new LinkedList<Character>();
        for (Character c: degree.keySet()) {
            if (degree.get(c) == 0) {
                queue.add(c);
            }
        }
        while (!queue.isEmpty()) {
            Character c = queue.poll();
            ret.append(c);

            HashSet<Character> setForCurrentChar = hashMap.get(c);
            if (setForCurrentChar != null) {
                for (Character ch: setForCurrentChar) {
                    int indegree = degree.get(ch);
                    degree.put(ch, indegree - 1);
                    if (indegree - 1 == 0) {
                        queue.add(ch);
                    }
                }
            }
        }
        String retString = ret.toString();
        if (retString.length() != degree.size()) return "";
        return retString;
    }


































}
