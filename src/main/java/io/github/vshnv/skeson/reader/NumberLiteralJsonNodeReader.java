package io.github.vshnv.skeson.reader;

import io.github.vshnv.skeson.parse.IndexedString;

import static io.github.vshnv.skeson.parse.ParsingUtils.skipSpaces;

public class NumberLiteralJsonNodeReader implements JsonNodeReader<Number> {
    @Override
    public Number read(final IndexedString indexedString) {
        skipSpaces(indexedString);
        final StringBuilder buffer = new StringBuilder();
        char c = indexedString.getCharAtIndex();
        boolean decimalPassed = false;
        while (Character.isDigit(c) || (!decimalPassed && c == '.')){
            if (!decimalPassed && c == '.') {
                decimalPassed = true;
            }
            buffer.append(c);
            indexedString.incrementIndex();
            c = indexedString.getCharAtIndex();
        }
        final Number number = Double.parseDouble(buffer.toString());
        return number;
    }
}
