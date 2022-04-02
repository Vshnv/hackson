package io.github.vshnv.skeson;

import io.github.vshnv.skeson.parse.IndexedString;
import io.github.vshnv.skeson.reader.JsonNodeReader;

import static io.github.vshnv.skeson.parse.ParsingUtils.skipSpaces;

public class LiteralReadingJsonNode<T> implements JsonNode {
    private final JsonNodeReader<T> nodeReader;
    private final LiteralJsonNode<T> node;

    public LiteralReadingJsonNode(final JsonNodeReader<T> nodeReader, final LiteralJsonNode<T> node) {
        this.nodeReader = nodeReader;
        this.node = node;
    }


    @Override
    public void writeTo(final StringBuffer buffer) {
        if (node == null) {
            buffer.append("null").append(' ');
            return;
        }
        node.writeTo(buffer);
    }

    @Override
    public void match(final IndexedString indexedString) {
        skipSpaces(indexedString);
        final T t = nodeReader.read(indexedString);
        node.setValue(t);
    }

    public T getValue() {
        return node.getValue();
    }
}
