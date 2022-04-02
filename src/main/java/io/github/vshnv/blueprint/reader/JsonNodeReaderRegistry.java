package io.github.vshnv.blueprint.reader;

import java.util.Map;

public final class JsonNodeReaderRegistry {
    private final Map<Class, JsonNodeReader<?>> readers;

    public JsonNodeReaderRegistry(final Map<Class, JsonNodeReader<?>> readers) {
        this.readers = readers;
    }

    public <T> JsonNodeReader<T> getReader(final Class<T> clazz) {
        return (JsonNodeReader<T>) readers.get(clazz);
    }

    public <T> void register(final Class<T> clazz, final JsonNodeReader<T> reader) {
        readers.put(clazz, reader);
    }

    public void unregisterReader(final Class clazz) {
        readers.remove(clazz);
    }
}
