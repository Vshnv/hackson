package io.github.vshnv.hackson;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static io.github.vshnv.hackson.Json.*;
import static org.junit.jupiter.api.Assertions.*;

class ObjectJsonNodeTest {
    private static final Gson GSON = new Gson();
    @Test
    public void testJsonEncode() {
        final JsonNode node = obj(
                name -> lit("Josh"),
                age -> lit(25),
                address -> obj(
                        street -> lit("Main Street"),
                        city -> lit("New York"),
                        state -> lit("NY")
                ),
                friends -> arr(
                        obj(
                                name -> lit("John")
                        ),
                        obj(
                                name -> lit("Jane")
                        )
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
        final JsonObject john = new JsonObject();
        john.addProperty("name", "John");
        final JsonObject jane = new JsonObject();
        jane.addProperty("name", "Jane");
        final JsonArray friends = new JsonArray();
        friends.add(john);
        friends.add(jane);
        expectedObject.add("friends", friends);
        final JsonObject actualObject = GSON.fromJson(node.toString(), JsonObject.class);
        assertEquals(expectedObject, actualObject);
    }
}