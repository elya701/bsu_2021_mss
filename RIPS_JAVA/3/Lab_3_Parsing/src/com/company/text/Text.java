package com.company.text;

import com.company.localization.LocaleManager;
import exceptions.NoWordsException;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class representing text
 */
public class Text extends BaseComplexTextUnit {

    /**
     * Builds String representation of Text. It concatenates paragraphs,
     * code blocks and sentences
     * @return String representation of Text
     */
    @Override
    public String build() {
        StringBuilder textBuilder = new StringBuilder(units.get(0).build());

        for (int i = 1; i < units.size(); i++) {
            if (!(units.get(i - 1) instanceof Paragraph))
                textBuilder.append(" ");

            textBuilder.append(units.get(i).build());
        }

        return textBuilder.toString();
    }

    /**
     * Returns all words in the text
     * @return words
     */
    public ArrayList<Word> getWords() {
        ArrayList<Word> result = new ArrayList<>();

        for (TextUnit unit : units) {
            if (unit instanceof Sentence) {
                result.addAll(((Sentence) unit).getWords());
            }
        }

        return result;
    }
    
    public ArrayList<Sentence> getSentences() {
        ArrayList<Sentence> result = new ArrayList<>();

        for (TextUnit unit : units) {
            if (unit instanceof Sentence) {
                result.add((Sentence) unit);
            }
        }

        return result;
    }

    /**
     * Returns an array of all words from the text sorted by vowels share
     * @return words
     */
    public List<Word> sortWordsByVowelsShare() {
        ArrayList<Word> result = getWords();

        result.sort(Comparator.comparingDouble(Word::getVowelsShare));

        return result;
    }
    
    public void replaceFirstAndLastWords() {
        ArrayList<Sentence> allSentences = getSentences();

        for (Sentence sentence : allSentences) {
            ArrayList<Word> words = sentence.getWords();
            Word last_element = words.get(words.size() - 1);
            words.set(words.size() - 1, words.get(0));
            words.set(0, last_element);

            sentence.setWords(words);
        }
    }

    /**
     * Returns an array of words with the first vowel sorted by first consonant
     * @return words
     */
    public List<Word> sortWordsWithFirstVowelByFirstConsonant() throws NoWordsException {
        ArrayList<Word> allWords = getWords();

        if (allWords == null)
            throw new NoWordsException("no words");

        String firstVowel = String.format("^[%s].*", LocaleManager.getLocalizedString(Word.VOWELS));

        return allWords.stream()
                .filter(s -> s.build().toLowerCase().matches(firstVowel))
                .sorted((a, b) -> {
                    Character firstConsonantA = a.getFirstConsonant();
                    Character firstConsonantB = b.getFirstConsonant();

                    if (firstConsonantA == null) {
                        if (firstConsonantB == null) {
                            return 0;
                        }

                        return 1;
                    } else if (firstConsonantB == null) {
                        return -1;
                    }

                    return firstConsonantA.compareTo(firstConsonantB);
                }).collect(Collectors.toList());
    }

    

    /**
     * Add unit to Text. Text amy contain Sentence, Code and Paragraph.
     * @param unit
     *        Unit to be added
     */
    @Override
    public void addUnit(TextUnit unit) {
        if (!(unit instanceof Sentence || unit instanceof Code || unit instanceof Paragraph))
            throw new IllegalArgumentException("Text may contain only Sentence and Code");

        super.addUnit(unit);
    }
}