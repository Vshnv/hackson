package io.github.vshnv.skeson.literal;

import io.github.vshnv.skeson.parse.IndexedString;
import io.github.vshnv.skeson.LiteralJsonNode;
import io.github.vshnv.skeson.reader.JsonNodeReader;

import static io.github.vshnv.skeson.parse.ParsingUtils.parseAssert;

public final class NumberLiteralJsonNode implements LiteralJsonNode<Number> {
    private final JsonNodeReader<Number> reader;
    private Number number;

    public NumberLiteralJsonNode(final JsonNodeReader<Number> reader, final Number number) {
        this.reader = reader;
        this.number = number;
    }

    @Override
    public void writeTo(final StringBuffer buffer) {
        buffer.append(number);
    }

    @Override
    public void match(final IndexedString indexedString) {
        final Number number = reader.read(indexedString);
        parseAssert(approxEquals(this.number, number));
    }

    @Override
    public Number getValue() {
        return number;
    }

    @Override
    public void setValue(final Number number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return stringify();
    }

    private static boolean approxEquals(final Number a, final Number b) {
        if (a == null) {
            return b == null;
        } else if (b == null) {
            return false;
        }

        return Math.abs(a.floatValue() - b.floatValue()) < 0.0000001;
    }
}
