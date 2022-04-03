package io.github.vshnv.skeson;

public interface ReadingJsonNode<T> extends JsonNode {
    T getValue();
}
