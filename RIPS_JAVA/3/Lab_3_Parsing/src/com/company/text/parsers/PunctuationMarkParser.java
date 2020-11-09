package com.company.text.parsers;

import com.company.localization.LocaleManager;
import com.company.text.PunctuationMark;
import exceptions.InvalidPunctuationMark;

/**
 * Parser used for parsing punctuation marks
 */
public class PunctuationMarkParser implements Parser {

    /**
     * Parses punctuation mark from punctuationMarkString
     * @param punctuationMarkString
     *        String to be parsed
     * @return PunctuationMark - punctuation mark
     */
    @Override
    public PunctuationMark parse(String punctuationMarkString) throws InvalidPunctuationMark {
        for (PunctuationMark punctuationMark : PunctuationMark.values()) {
            if (punctuationMark.toString().equals(punctuationMarkString))
                return punctuationMark;
        }

        throw new InvalidPunctuationMark(LocaleManager.getLocalizedString(
                LocaleManager.ILLEGAL_PUNCTUATION_MARK));
    }

}