package io.github.vshnv.skeson.adapter;

import io.github.vshnv.skeson.JsonNode;

import java.util.function.Consumer;

public interface JsonAdapter<T> {
    JsonNode toJson(T object);
    T fromJson(final Consumer<JsonNode> readNode);
}
