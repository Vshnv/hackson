package io.github.vshnv.hackson;

public interface JsonNode {
    void writeTo(final StringBuffer buffer);
    void match(final IndexedString indexedString);
}
