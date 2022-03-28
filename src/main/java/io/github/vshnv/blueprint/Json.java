package io.github.vshnv.blueprint;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Json {
    private Json() {
        throw new AssertionError("No instances.");
    }

    public static JsonNode obj(final ObjectEntry... nodes) {
        final Map<String, JsonNode> nodeMap = new LinkedHashMap<>();
        for (final ObjectEntry entry : nodes) {
            nodeMap.put(entry.name(), entry.value());
        }
        return new ObjectJsonNode(nodeMap);
    }

    public static JsonNode arr(final JsonNode... nodes) {
        return new ArrayJsonNode(Arrays.asList(nodes));
    }

    public static JsonNode lit(final Number value) {
        return new NumberLiteralJsonNode(value);
    }

    public static JsonNode lit(final String value) {
        return new StringLiteralJsonNode(value);
    }

    public static JsonNode matchNumber() {
        return new NumberLiteralJsonNode(null);
    }

    public static JsonNode matchString() {
        return new StringLiteralJsonNode(null);
    }
}