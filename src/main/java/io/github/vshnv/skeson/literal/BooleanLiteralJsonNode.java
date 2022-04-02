package io.github.vshnv.skeson.literal;

import io.github.vshnv.skeson.parse.IndexedString;
import io.github.vshnv.skeson.LiteralJsonNode;
import io.github.vshnv.skeson.parse.ParsingUtils;
import io.github.vshnv.skeson.reader.JsonNodeReader;

public class BooleanLiteralJsonNode implements LiteralJsonNode<Boolean> {
    private final JsonNodeReader<Boolean> reader;
    private Boolean value;

    public BooleanLiteralJsonNode(final JsonNodeReader<Boolean> reader, final Boolean value) {
        this.reader = reader;
        this.value = value;
    }

    @Override
    public void writeTo(final StringBuffer buffer) {
        buffer.append(getValue());
    }

    @Override
    public void match(final IndexedString indexedString) {
        final Boolean value = reader.read(indexedString);
        ParsingUtils.parseAssert(value);
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(final Boolean aBoolean) {
        this.value = aBoolean;
    }
}
