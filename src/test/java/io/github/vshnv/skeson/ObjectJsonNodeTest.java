package io.github.vshnv.skeson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static io.github.vshnv.skeson.Json.*;
import static org.junit.jupiter.api.Assertions.*;

class ObjectJsonNodeTest {
    private static final Gson GSON = new Gson();

    @Test
    public void testSimpleObject() {
        final JsonNode node = obj(
                name -> lit("Josh"),
                age -> lit(25),
                nickname -> lit("Tesla")
        );
        final JsonObject expectedObject = new JsonObject();
        expectedObject.addProperty("name", "Josh");
        expectedObject.addProperty("age", 25);
        expectedObject.addProperty("nickname", "Tesla");
        final JsonObject actualObject = GSON.fromJson(node.toString(), JsonObject.class);
        assertEquals(expectedObject, actualObject);
    }

    @Test
    public void testComplexObject() {
        final JsonNode node = obj(
                name -> lit("Josh"),
                age -> lit(25),
                address -> obj(
                        street -> lit("Main Street"),
                        city -> lit("New York"),
                        state -> lit("NY")
                ),
                friend -> obj(
                        name -> lit("John")
                )
        );
        final JsonObject expectedObject = new JsonObject();
        expectedObject.addProperty("name", "Josh");
        expectedObject.addProperty("age", 25);
        final JsonObject address = new JsonObject();
        address.addProperty("street", "Main Street");
        address.addProperty("city", "New York");
        address.addProperty("state", "NY");
        expectedObject.add("address", address);
        final JsonObject friend = new JsonObject();
        friend.addProperty("name", "John");
        expectedObject.add("friend", friend);
        final JsonObject actualObject = GSON.fromJson(node.toString(), JsonObject.class);
        assertEquals(expectedObject, actualObject);
    }
}