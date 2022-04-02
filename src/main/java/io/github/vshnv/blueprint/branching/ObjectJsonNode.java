package io.github.vshnv.blueprint.branching;

import io.github.vshnv.blueprint.IndexedString;
import io.github.vshnv.blueprint.JsonNode;
import io.github.vshnv.blueprint.literal.StringLiteralJsonNode;
import io.github.vshnv.blueprint.reader.JsonNodeReader;

import java.util.Map;

import static io.github.vshnv.blueprint.ParsingUtils.*;

public final class ObjectJsonNode implements JsonNode {
    private final JsonNodeReader<String> stringJsonNodeReader;
    private final Map<String, JsonNode> children;

    public ObjectJsonNode(final JsonNodeReader<String> stringJsonNodeReader, final Map<String, JsonNode> children) {
        this.stringJsonNodeReader = stringJsonNodeReader;
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
            final String key = stringJsonNodeReader.read(indexedString);
            skipSpaces(indexedString);
            parseAssert(indexedString.getCharAtIndex() == ':');
            indexedString.incrementIndex();
            skipSpaces(indexedString);
            children.get(key).match(indexedString);
            skipSpaces(indexedString);
            if (indexedString.getCharAtIndex() == ',') {
                indexedString.incrementIndex();
            } else {
                break;
            }
        }
        skipSpaces(indexedString);
        parseAssert(indexedString.getCharAtIndex() == '}');
        indexedString.incrementIndex();
    }

    @Override
    public String toString() {
        return stringify();
    }
}
