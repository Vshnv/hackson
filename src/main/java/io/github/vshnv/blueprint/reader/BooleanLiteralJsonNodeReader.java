package io.github.vshnv.blueprint.reader;

import io.github.vshnv.blueprint.IndexedString;
import io.github.vshnv.blueprint.LiteralJsonNode;
import io.github.vshnv.blueprint.ParsingUtils;
import io.github.vshnv.blueprint.literal.BooleanLiteralJsonNode;

public class BooleanLiteralJsonNodeReader implements JsonNodeReader<Boolean> {
    @Override
    public Boolean read(final IndexedString input) {
        final String value = ParsingUtils.matchAny(input, "true", "false");
        return value.equals("true");
    }
}
