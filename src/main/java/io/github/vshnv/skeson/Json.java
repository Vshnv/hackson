package io.github.vshnv.skeson;

import io.github.vshnv.skeson.branching.ArrayJsonNode;
import io.github.vshnv.skeson.branching.ObjectJsonNode;
import io.github.vshnv.skeson.literal.BooleanLiteralJsonNode;
import io.github.vshnv.skeson.literal.NumberLiteralJsonNode;
import io.github.vshnv.skeson.literal.StringLiteralJsonNode;
import io.github.vshnv.skeson.parse.ObjectEntry;
import io.github.vshnv.skeson.reader.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Json {
    private static final JsonNodeReaderRegistry GLOBAL_READER_REGISTRY = new JsonNodeReaderRegistry(new HashMap<>());
    static {
        GLOBAL_READER_REGISTRY.register(ObjectJsonNode.class, new ObjectJsonNodeReader(GLOBAL_READER_REGISTRY));
        GLOBAL_READER_REGISTRY.register(ArrayJsonNode.class, new ArrayJsonNodeReader(GLOBAL_READER_REGISTRY));
        GLOBAL_READER_REGISTRY.register(Boolean.class, new BooleanLiteralJsonNodeReader());
        GLOBAL_READER_REGISTRY.register(Number.class, new NumberLiteralJsonNodeReader());
        GLOBAL_READER_REGISTRY.register(String.class, new StringLiteralJsonNodeReader());
    }

    private Json() {
        throw new AssertionError("No instances.");
    }

    public static JsonNode obj(final ObjectEntry... nodes) {
        final Map<String, JsonNode> nodeMap = new LinkedHashMap<>();
        for (final ObjectEntry entry : nodes) {
            nodeMap.put(entry.name(), entry.value());
        }
        return new ObjectJsonNode(GLOBAL_READER_REGISTRY.getReader(String.class), nodeMap);
    }

    public static JsonNode arr(final JsonNode... nodes) {
        return new ArrayJsonNode(Arrays.asList(nodes));
    }

    public static JsonNode lit(final Number value) {
        return new NumberLiteralJsonNode(GLOBAL_READER_REGISTRY.getReader(Number.class), value);
    }

    public static JsonNode lit(final String value) {
        return new StringLiteralJsonNode(GLOBAL_READER_REGISTRY.getReader(String.class), value);
    }

    public static JsonNode lit(final Boolean value) {
        return new BooleanLiteralJsonNode(GLOBAL_READER_REGISTRY.getReader(Boolean.class), value);
    }

    public static JsonNode matchArray() {
        return new LiteralReadingJsonNode<>(GLOBAL_READER_REGISTRY.getReader(ArrayJsonNode.class), null);
    }

    public static JsonNode matchObject() {
        return new LiteralReadingJsonNode<>(GLOBAL_READER_REGISTRY.getReader(ObjectJsonNode.class), null);
    }

    public static JsonNode matchBoolean() {
        return new LiteralReadingJsonNode<>(GLOBAL_READER_REGISTRY.getReader(Boolean.class), null);
    }

    public static JsonNode matchNumber() {
        return new LiteralReadingJsonNode<>(GLOBAL_READER_REGISTRY.getReader(Number.class), null);
    }

    public static JsonNode matchString() {
        return new StringLiteralJsonNode(GLOBAL_READER_REGISTRY.getReader(String.class), null);
    }
}
