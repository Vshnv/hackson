package io.github.vshnv.hackson;

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
}
