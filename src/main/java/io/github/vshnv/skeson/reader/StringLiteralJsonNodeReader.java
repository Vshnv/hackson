package io.github.vshnv.skeson.reader;

import io.github.vshnv.skeson.parse.IndexedString;

import static io.github.vshnv.skeson.parse.ParsingUtils.parseAssert;
import static io.github.vshnv.skeson.parse.ParsingUtils.skipSpaces;

public class StringLiteralJsonNodeReader implements JsonNodeReader<String> {

    @Override
    public String read(final IndexedString indexedString) {
        skipSpaces(indexedString);
        final StringBuilder buffer = new StringBuilder();
        parseAssert(indexedString.getCharAtIndex() == '"');
        indexedString.incrementIndex();
        while (indexedString.getCharAtIndex() != '"') {
            buffer.append(indexedString.getCharAtIndex());
            indexedString.incrementIndex();
        }
        indexedString.incrementIndex();
        return buffer.toString();
    }
}
