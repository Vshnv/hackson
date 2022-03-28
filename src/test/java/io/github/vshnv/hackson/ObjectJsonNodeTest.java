package io.github.vshnv.hackson;

import static io.github.vshnv.hackson.Json.*;
import static org.junit.jupiter.api.Assertions.*;

class ObjectJsonNodeTest {
    public void testJsonEncode() {
        final JsonNode node = obj(
                name -> lit("ABCDEF"),
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

    }
}