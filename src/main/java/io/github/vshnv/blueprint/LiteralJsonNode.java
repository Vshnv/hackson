package io.github.vshnv.blueprint;

public interface LiteralJsonNode<T> extends JsonNode {
    T getValue();
    void setValue(T t);
}
