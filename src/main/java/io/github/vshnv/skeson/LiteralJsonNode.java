package io.github.vshnv.skeson;

public interface LiteralJsonNode<T> extends JsonNode {
    T getValue();
    void setValue(T t);
}
