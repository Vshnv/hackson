package io.github.vshnv.hackson;

public interface LiteralJsonNode<T> extends JsonNode {
    T getValue();
    void setValue(T t);
}
