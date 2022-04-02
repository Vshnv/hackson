package io.github.vshnv.blueprint;

import io.github.vshnv.blueprint.reader.JsonNodeReader;

public interface JsonNode {
    void writeTo(final StringBuffer buffer);
    void match(final IndexedString indexedString);
    default String stringify() {
        final StringBuffer buffer = new StringBuffer();
        writeTo(buffer);
        return buffer.toString();
    }
}
