package io.github.vshnv.blueprint.reader;

import io.github.vshnv.blueprint.IndexedString;
import io.github.vshnv.blueprint.JsonNode;

public interface JsonNodeReader<T> {
    T read(final IndexedString input);
}
