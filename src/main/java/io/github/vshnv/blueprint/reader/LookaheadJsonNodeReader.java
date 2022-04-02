package io.github.vshnv.blueprint.reader;

import io.github.vshnv.blueprint.IndexedString;
import io.github.vshnv.blueprint.JsonNode;
import io.github.vshnv.blueprint.ParsingUtils;
import io.github.vshnv.blueprint.branching.ArrayJsonNode;
import io.github.vshnv.blueprint.literal.BooleanLiteralJsonNode;
import io.github.vshnv.blueprint.literal.NumberLiteralJsonNode;
import io.github.vshnv.blueprint.literal.StringLiteralJsonNode;

import static io.github.vshnv.blueprint.ParsingUtils.parseAssert;
import static io.github.vshnv.blueprint.ParsingUtils.skipSpaces;

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
