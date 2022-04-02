package io.github.vshnv.skeson.adapter;

import io.github.vshnv.skeson.JsonNode;
import io.github.vshnv.skeson.LiteralReadingJsonNode;
import io.github.vshnv.skeson.parse.IndexedString;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.function.Consumer;

import static io.github.vshnv.skeson.Json.*;

class JsonAdapterTest {

    @Test
    void testAdapter() {
        final Human a = new Human("Joe", 25, 100);
        final JsonAdapter<Human> humanJsonAdapter = new HumanJsonAdapter();
        final JsonNode jsonNode = humanJsonAdapter.toJson(a);
        final String json = jsonNode.toString();
        System.out.println("JSON: " + json);
        final IndexedString indexedString = new IndexedString(json, 0);
        final Human b = humanJsonAdapter.fromJson(node -> node.match(indexedString));
        assert a.equals(b);
    }

    static class Human {
        private final String name;
        private final int age;
        private final int height;

        Human(String name, int age, int height) {
            this.name = name;
            this.age = age;
            this.height = height;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public int getHeight() {
            return height;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Human human = (Human) o;
            return getAge() == human.getAge() && getHeight() == human.getHeight() && Objects.equals(getName(), human.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getName(), getAge(), getHeight());
        }
    }

    static class HumanJsonAdapter implements JsonAdapter<Human> {

        @Override
        public JsonNode toJson(final Human object) {
            final String objectName = object.getName();
            final int objectAge = object.getAge();
            final int objectHeight = object.getHeight();
            return obj(
                    name -> lit(objectName),
                    age -> lit(objectAge),
                    height -> lit(objectHeight)
            );
        }

        @Override
        public Human fromJson(final Consumer<JsonNode> readNode) {
            final LiteralReadingJsonNode<String> nameMatcher = matchString();
            final LiteralReadingJsonNode<Number> ageMatcher = matchNumber();
            final LiteralReadingJsonNode<Number> heightMatcher = matchNumber();
            readNode.accept(obj(
                    name -> nameMatcher,
                    age -> ageMatcher,
                    height -> heightMatcher
            ));
            return new Human(nameMatcher.getValue(), ageMatcher.getValue().intValue(), heightMatcher.getValue().intValue());
        }
    }
}