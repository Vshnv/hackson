package io.github.vshnv.skeson.reader;

import io.github.vshnv.skeson.parse.IndexedString;
import io.github.vshnv.skeson.JsonNode;
import io.github.vshnv.skeson.branching.ArrayJsonNode;
import io.github.vshnv.skeson.literal.BooleanLiteralJsonNode;
import io.github.vshnv.skeson.literal.NumberLiteralJsonNode;
import io.github.vshnv.skeson.literal.StringLiteralJsonNode;

import static io.github.vshnv.skeson.parse.ParsingUtils.skipSpaces;

public class LookaheadJsonNodeReader implements JsonNodeReader<JsonNode> {
    private final JsonNodeReaderRegistry nodeReaderRegistry;

    public LookaheadJsonNodeReader(final JsonNodeReaderRegistry nodeReaderRegistry) {
        this.nodeReaderRegistry = nodeReaderRegistry;
    }

    @Override
    public JsonNode read(final IndexedString input) {
        final JsonNodeReader<String> stringReader = nodeReaderRegistry.getReader(String.class);
        skipSpaces(input);
        switch (input.getCharAtIndex()) {
            case '"':
                return new StringLiteralJsonNode(stringReader, stringReader.read(input));
            case '{':
                return read(input);
            case '[':
                return nodeReaderRegistry.getReader(ArrayJsonNode.class).read(input);
            case 't':
            case 'f':
                final JsonNodeReader<Boolean> booleanReader = nodeReaderRegistry.getReader(Boolean.class);
                return new BooleanLiteralJsonNode(booleanReader, booleanReader.read(input));
            default:
                final JsonNodeReader<Number> numberReader = nodeReaderRegistry.getReader(Number.class);
                return new NumberLiteralJsonNode(numberReader, numberReader.read(input));
        }
    }
}
