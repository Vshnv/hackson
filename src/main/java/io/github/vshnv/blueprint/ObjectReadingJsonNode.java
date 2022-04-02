package io.github.vshnv.blueprint;

import io.github.vshnv.blueprint.branching.ObjectJsonNode;
import io.github.vshnv.blueprint.reader.JsonNodeReader;
import io.github.vshnv.blueprint.reader.ObjectJsonNodeReader;

import static io.github.vshnv.blueprint.ParsingUtils.skipSpaces;

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

