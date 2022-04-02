package io.github.vshnv.skeson.reader;

import io.github.vshnv.skeson.parse.IndexedString;

public interface JsonNodeReader<T> {
    T read(final IndexedString input);
}
