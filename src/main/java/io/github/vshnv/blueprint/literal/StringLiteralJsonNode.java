package io.github.vshnv.blueprint.literal;

import io.github.vshnv.blueprint.IndexedString;
import io.github.vshnv.blueprint.LiteralJsonNode;
import io.github.vshnv.blueprint.reader.JsonNodeReader;

import java.util.Objects;

import static io.github.vshnv.blueprint.ParsingUtils.parseAssert;
import static io.github.vshnv.blueprint.ParsingUtils.skipSpaces;

public class StringLiteralJsonNode implements LiteralJsonNode<String> {
    private final JsonNodeReader<String> reader;
    private String value;

    public StringLiteralJsonNode(final JsonNodeReader<String> reader, final String value) {
        this.reader = reader;
        this.value = value;
    }

    @Override
    public void writeTo(final StringBuffer buffer) {
        buffer.append('"').append(value).append('"');
    }

    @Override
    public void match(final IndexedString indexedString) {
        final String value = reader.read(indexedString);
        parseAssert(Objects.equals(this.value, value));
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
