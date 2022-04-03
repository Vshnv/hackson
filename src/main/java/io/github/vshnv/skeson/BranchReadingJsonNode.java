package io.github.vshnv.skeson;

import io.github.vshnv.skeson.branching.ObjectJsonNode;
import io.github.vshnv.skeson.parse.IndexedString;
import io.github.vshnv.skeson.reader.JsonNodeReader;

import static io.github.vshnv.skeson.parse.ParsingUtils.skipSpaces;

public class BranchReadingJsonNode<T extends JsonNode> implements ReadingJsonNode<T> {
    private final JsonNodeReader<T> nodeReader;
    private T node;

    public BranchReadingJsonNode(final JsonNodeReader<T> nodeReader, final T node) {
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
        this.node = nodeReader.read(indexedString);
    }

    @Override
    public T getValue() {
        return this.node;
    }
}

