package io.github.vshnv.blueprint.reader;

import io.github.vshnv.blueprint.IndexedString;
import io.github.vshnv.blueprint.JsonNode;
import io.github.vshnv.blueprint.branching.ArrayJsonNode;
import io.github.vshnv.blueprint.branching.ObjectJsonNode;
import io.github.vshnv.blueprint.literal.BooleanLiteralJsonNode;
import io.github.vshnv.blueprint.literal.NumberLiteralJsonNode;
import io.github.vshnv.blueprint.literal.StringLiteralJsonNode;

import java.util.HashMap;
import java.util.Map;

import static io.github.vshnv.blueprint.ParsingUtils.parseAssert;
import static io.github.vshnv.blueprint.ParsingUtils.skipSpaces;

public class ObjectJsonNodeReader implements JsonNodeReader<ObjectJsonNode> {
    private final JsonNodeReaderRegistry nodeReaderRegistry;

    public ObjectJsonNodeReader(final JsonNodeReaderRegistry nodeReaderRegistry) {
        this.nodeReaderRegistry = nodeReaderRegistry;
    }


    @Override
    public ObjectJsonNode read(final IndexedString input) {
        final Map<String, JsonNode> result = new HashMap<>();
        final JsonNodeReader<String> stringReader = nodeReaderRegistry.getReader(String.class);
        skipSpaces(input);
        parseAssert(input.getCharAtIndex() == '{');
        input.incrementIndex();
        while (input.getCharAtIndex() != '}') {
            final String key = stringReader.read(input);
            parseAssert(input.getCharAtIndex() == ':');
            input.incrementIndex();
            skipSpaces(input);
            final JsonNode value = nodeReaderRegistry.getReader(JsonNode.class).read(input);
            result.put(key, value);
            skipSpaces(input);
            if (input.getCharAtIndex() == ',') {
                input.incrementIndex();
            } else {
                break;
            }
            skipSpaces(input);
        }
        skipSpaces(input);
        parseAssert(input.getCharAtIndex() == '}');
        input.incrementIndex();
        return new ObjectJsonNode(stringReader, result);
    }
}
