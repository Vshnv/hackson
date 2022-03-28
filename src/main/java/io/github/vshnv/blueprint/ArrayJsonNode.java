package io.github.vshnv.blueprint;

import java.util.List;

import static io.github.vshnv.blueprint.ParsingUtils.parseAssert;
import static io.github.vshnv.blueprint.ParsingUtils.skipSpaces;

public final class ArrayJsonNode implements JsonNode {
    private final List<JsonNode> nodeList;

    public ArrayJsonNode(final List<JsonNode> nodeList) {
        this.nodeList = nodeList;
    }

    public List<JsonNode> getChildren() {
        return nodeList;
    }

    @Override
    public void writeTo(final StringBuffer buffer) {
        buffer.append('[');
        for (final JsonNode node: nodeList) {
            node.writeTo(buffer);
            buffer.append(',');
        }
        if (buffer.charAt(buffer.length() - 1) == ',') {
            buffer.deleteCharAt(buffer.length() - 1);
        }
        buffer.append(']');
    }

    @Override
    public void match(final IndexedString indexedString) {
        skipSpaces(indexedString);
        parseAssert(indexedString.getCharAtIndex() == '[');
        indexedString.incrementIndex();
        int index = 0;
        while (index < nodeList.size()) {
            skipSpaces(indexedString);
            nodeList.get(index).match(indexedString);
            if (indexedString.getCharAtIndex() == ',') {
                indexedString.incrementIndex();
            } else {
                break;
            }
        }
        parseAssert(indexedString.getCharAtIndex() == ']');
        indexedString.incrementIndex();
    }

    @Override
    public String toString() {
        return stringify();
    }
}
