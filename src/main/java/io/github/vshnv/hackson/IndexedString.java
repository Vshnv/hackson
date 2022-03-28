package io.github.vshnv.hackson;

public class IndexedString {
    private final String string;
    private final int length;
    private int index;

    public IndexedString(final String string, final int index) {
        this.string = string;
        this.length = string.length();
        this.index = index;
    }

    public String getString() {
        return string;
    }

    public int getIndex() {
        return index;
    }

    public char getCharAtIndex() {
        return string.charAt(index);
    }

    public boolean incrementIndex() {
        if (index >= length) return false;
        index++;
        return true;
    }
}
