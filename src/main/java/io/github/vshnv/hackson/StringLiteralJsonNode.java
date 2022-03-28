package io.github.vshnv.hackson;

import static io.github.vshnv.hackson.ParsingUtils.parseAssert;
import static io.github.vshnv.hackson.ParsingUtils.skipSpaces;

public class StringLiteralJsonNode implements LiteralJsonNode<String> {
    private String value;

    public StringLiteralJsonNode(final String value) {
        this.value = value;
    }

    @Override
    public void writeTo(final StringBuffer buffer) {
        buffer.append('"').append(value).append('"');
    }

    @Override
    public void match(final IndexedString indexedString) {
        skipSpaces(indexedString);
        final StringBuilder buffer = new StringBuilder();
        parseAssert(indexedString.getCharAtIndex() == '"');
        indexedString.incrementIndex();
        while (indexedString.getCharAtIndex() != '"') {
            buffer.append(indexedString.getCharAtIndex());
            indexedString.incrementIndex();
        }
        indexedString.incrementIndex();
        final String value = buffer.toString();
        if (this.value == null) {
            this.value = value;
        } else {
            parseAssert(this.value.equals(value));
        }
    }


    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return stringify();
    }
}
