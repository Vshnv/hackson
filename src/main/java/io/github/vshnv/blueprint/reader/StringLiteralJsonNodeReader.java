package io.github.vshnv.blueprint.reader;

import io.github.vshnv.blueprint.IndexedString;

import static io.github.vshnv.blueprint.ParsingUtils.parseAssert;
import static io.github.vshnv.blueprint.ParsingUtils.skipSpaces;

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
