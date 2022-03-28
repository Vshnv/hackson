package io.github.vshnv.hackson;

import java.util.Map;

import static io.github.vshnv.hackson.ParsingUtils.*;

public final class ObjectJsonNode implements JsonNode {
    private final Map<String, JsonNode> children;

    public ObjectJsonNode(final Map<String, JsonNode> children) {
        this.children = children;
    }

    public Map<String, JsonNode> getChildren() {
        return children;
    }

    @Override
    public void writeTo(final StringBuffer buffer) {
        buffer.append("{");
        for (final Map.Entry<String, JsonNode> entry : children.entrySet()) {
            buffer.append('"').append(entry.getKey()).append('"').append(':').append(' ');
            entry.getValue().writeTo(buffer);
            buffer.append(',');
        }
        if (buffer.charAt(buffer.length() - 1) == ',') {
            buffer.deleteCharAt(buffer.length() - 1);
        }
        buffer.append("}");
    }

    @Override
    public void match(final IndexedString indexedString) {
        parseAssert(indexedString.getCharAtIndex() == '{');
        indexedString.incrementIndex();
        while (indexedString.getCharAtIndex() != '}') {
            skipSpaces(indexedString);
            final StringLiteralJsonNode stringLiteralJsonNode = new StringLiteralJsonNode(null);
            stringLiteralJsonNode.match(indexedString);
            skipSpaces(indexedString);
            parseAssert(indexedString.getCharAtIndex() == ':');
            indexedString.incrementIndex();
            skipSpaces(indexedString);
            children.get(stringLiteralJsonNode.getValue()).match(indexedString);
            skipSpaces(indexedString);
            if (indexedString.getCharAtIndex() == ',') {
                indexedString.incrementIndex();
            } else {
                break;
            }
        }
        parseAssert(indexedString.getCharAtIndex() == '}');
        indexedString.incrementIndex();
    }

    @Override
    public String toString() {
        return stringify();
    }
}
