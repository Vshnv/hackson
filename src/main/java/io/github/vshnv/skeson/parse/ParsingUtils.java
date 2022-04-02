package io.github.vshnv.skeson.parse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParsingUtils {
    private ParsingUtils() {
        throw new AssertionError("No instances");
    }

    public static void parseAssert(boolean condition) {
        if (!condition) {
            throw new IllegalStateException();
        }
    }

    public static void skipSpaces(final IndexedString s) {
        while (s.getCharAtIndex() == ' ') {
            s.incrementIndex();
        }
    }

    public static String matchAny(final IndexedString s, final String... words) {
        skipSpaces(s);
        final List<String> matches = new ArrayList<>(Arrays.asList(words));
        int index = 0;
        while (matches.size() > 1 && index < s.getString().length()) {
            matches.removeIf(word -> word.charAt(index) != s.getCharAtIndex());
            s.incrementIndex();
        }
        parseAssert(matches.size() == 1);
        return matches.get(0);
    }
}
