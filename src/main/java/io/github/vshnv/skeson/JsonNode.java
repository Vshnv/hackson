package io.github.vshnv.skeson;

import io.github.vshnv.skeson.parse.IndexedString;

public interface JsonNode {
    void writeTo(final StringBuffer buffer);
    void match(final IndexedString indexedString);
    default String stringify() {
        final StringBuffer buffer = new StringBuffer();
        writeTo(buffer);
        return buffer.toString();
    }
}
