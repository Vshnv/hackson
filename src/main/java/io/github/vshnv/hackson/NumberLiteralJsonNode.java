package io.github.vshnv.hackson;

import static io.github.vshnv.hackson.ParsingUtils.parseAssert;
import static io.github.vshnv.hackson.ParsingUtils.skipSpaces;

public final class NumberLiteralJsonNode implements LiteralJsonNode<Number> {
    private Number number;

    public NumberLiteralJsonNode(final Number number) {
        this.number = number;
    }

    @Override
    public void writeTo(final StringBuffer buffer) {
        buffer.append(number);
    }

    @Override
    public void match(final IndexedString indexedString) {
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
        if (this.number == null) {
            this.number = number;
        } else {
            parseAssert(approxEquals(this.number.floatValue(), number.floatValue()));
        }
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

    private static boolean approxEquals(final double a, final double b) {
        return Math.abs(a - b) < 0.0000001;
    }
}
