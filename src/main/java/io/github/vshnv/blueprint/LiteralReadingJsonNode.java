package io.github.vshnv.blueprint;

import io.github.vshnv.blueprint.reader.JsonNodeReader;

import static io.github.vshnv.blueprint.ParsingUtils.skipSpaces;

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
}
