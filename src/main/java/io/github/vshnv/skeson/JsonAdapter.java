package io.github.vshnv.skeson;

public interface JsonAdapter<T> {
    JsonNode toJson(T object);
    T fromJson(JsonNode jsonNode);
}
