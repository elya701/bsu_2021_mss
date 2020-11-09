package com.company.text.parsers;

import com.company.text.Paragraph;
import com.company.text.TextUnit;

/**
 * Class used for parsing paragraphs
 */
public class ParagraphParser implements Parser {

    /**
     * Parses Paragraph from textUnit
     * @param textUnit
     *        String to be parsed
     * @return Paragraph - paragraph
     */
    @Override
    public TextUnit parse(String textUnit) {
        return new Paragraph();
    }
}