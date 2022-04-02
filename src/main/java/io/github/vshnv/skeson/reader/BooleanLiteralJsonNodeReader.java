package io.github.vshnv.skeson.reader;

import io.github.vshnv.skeson.parse.IndexedString;
import io.github.vshnv.skeson.parse.ParsingUtils;

public class BooleanLiteralJsonNodeReader implements JsonNodeReader<Boolean> {
    @Override
    public Boolean read(final IndexedString input) {
        final String value = ParsingUtils.matchAny(input, "true", "false");
        return value.equals("true");
    }
}
