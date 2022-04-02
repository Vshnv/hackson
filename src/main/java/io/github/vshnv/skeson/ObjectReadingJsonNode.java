package io.github.vshnv.skeson;

import io.github.vshnv.skeson.branching.ObjectJsonNode;
import io.github.vshnv.skeson.parse.IndexedString;
import io.github.vshnv.skeson.reader.JsonNodeReader;

import static io.github.vshnv.skeson.parse.ParsingUtils.skipSpaces;

public class ObjectReadingJsonNode<T> implements JsonNode {
    private final JsonNodeReader<T> nodeReader;
    private final ObjectJsonNode node;

    public ObjectReadingJsonNode(final JsonNodeReader<T> nodeReader, final ObjectJsonNode node) {
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

    }
}

