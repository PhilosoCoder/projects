package codekata.kata08conflictingobjectives;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConflictingObjects {

    public static void main(String[] args) {
        List<String> dictionary = readWordsFromFile("src/main/resources/dictionary.dat");

        Map<String,String> composedWordsReadable = findConcatenatedWordsMostReadable(dictionary);

        for (var word : composedWordsReadable.keySet()) {
            System.out.println(word + " + " + composedWordsReadable.get(word) + " => " + word + composedWordsReadable.get(word));
        }

        Map<String, String> composedWordsOptimized = findConcatenatedWordsMostOptimized(dictionary);

        for (var word : composedWordsOptimized.keySet()) {
            System.out.println(word + " + " + composedWordsOptimized.get(word) + " => " + word + composedWordsOptimized.get(word));
        }

        Map<String, String> composedWordsExtendable = findConcatenatedWordsMostExtendable(dictionary);

        for (var word : composedWordsExtendable.keySet()) {
            System.out.println(word + " + " + composedWordsExtendable.get(word) + " => " + word + composedWordsExtendable.get(word));
        }
    }

    private static Map<String, String> findConcatenatedWordsMostReadable(List<String> dictionary) {
        Map<String, String> composedWords = new HashMap<>();
        for (var word : dictionary) {
            if (word.length() != 6) {
                continue;
            }
            for (int i = 1; i < word.length(); i++) {
                String firstPart = word.substring(0, i);
                String secondPart = word.substring(i);
                if (dictionary.contains(firstPart) && dictionary.contains(secondPart)) {
                    composedWords.put(firstPart, secondPart);
                }
            }
        }
        return composedWords;
    }

    private static Map<String, String> findConcatenatedWordsMostOptimized(List<String> dictionary) {
        Set<String> wordsSet = new HashSet<>(dictionary);
        Map<String, String> composedWords = new HashMap<>();
        for (var word : wordsSet) {
            if (word.length() != 6) {
                continue;
            }
            for (int i = 1; i < word.length(); i++) {
                String firstPart = word.toLowerCase().substring(0, i);
                String secondPart = word.toLowerCase().substring(i);
                if (wordsSet.contains(firstPart) && wordsSet.contains(secondPart)) {
                    composedWords.put(firstPart, secondPart);
                }
            }
        }
        return composedWords;
    }

    private static Map<String, String> findConcatenatedWordsMostExtendable(List<String> dictionary) {
        Set<String> wordsSet = new HashSet<>(dictionary);
        Map<String, String> composedWords = new HashMap<>();
        for (var word : dictionary) {
            var composedWord = isConcatenatedMostExtendable(word, wordsSet);
            composedWords.putAll(composedWord);
        }
        return composedWords;
    }

    private static Map<String, String> isConcatenatedMostExtendable(String word, Set<String> dictionary) {
        Map<String, String> concatenatedWords = new HashMap<>();
        if (word.length() != 6) {
            return Collections.emptyMap();
        }
        for (int i = 1; i < word.length(); i++) {
            String firstPart = word.substring(0, i);
            String secondPart = word.substring(i);
            if (dictionary.contains(firstPart) && dictionary.contains(secondPart)) {
                concatenatedWords.put(firstPart, secondPart);
            }
        }
        return concatenatedWords;
    }

    private static List<String> readWordsFromFile(String filename) {
        List<String> words = new LinkedList<>();
        try (FileReader fileReader = new FileReader(filename);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                words.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return words;
    }
}
