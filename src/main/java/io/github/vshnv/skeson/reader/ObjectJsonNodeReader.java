package io.github.vshnv.skeson.reader;

import io.github.vshnv.skeson.parse.IndexedString;
import io.github.vshnv.skeson.JsonNode;
import io.github.vshnv.skeson.branching.ObjectJsonNode;

import java.util.HashMap;
import java.util.Map;

import static io.github.vshnv.skeson.parse.ParsingUtils.parseAssert;
import static io.github.vshnv.skeson.parse.ParsingUtils.skipSpaces;

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
