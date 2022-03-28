package io.github.vshnv.blueprint;

import java.util.HashMap;
import java.util.Map;

public class DefaultValue {
    private static final Map<Class<?>, Object> DEFAULT_VALUES = new HashMap<>();
    static {
        DEFAULT_VALUES.put(int.class, 0);
        DEFAULT_VALUES.put(Integer.class, 0);
        DEFAULT_VALUES.put(boolean.class, false);
        DEFAULT_VALUES.put(Boolean.class, false);
        DEFAULT_VALUES.put(byte.class, (byte)0);
        DEFAULT_VALUES.put(Byte.class, 0);
        DEFAULT_VALUES.put(char.class, ' ');
        DEFAULT_VALUES.put(Character.class, ' ');
        DEFAULT_VALUES.put(short.class, (short)0.0);
        DEFAULT_VALUES.put(Short.class, (short)0.0);
        DEFAULT_VALUES.put(long.class, 0L);
        DEFAULT_VALUES.put(Long.class, 0L);
        DEFAULT_VALUES.put(float.class, 0.0f);
        DEFAULT_VALUES.put(Float.class, 0.0f);
        DEFAULT_VALUES.put(double.class, 0.0d);
        DEFAULT_VALUES.put(Double.class, 0.0d);
    }

    @SuppressWarnings("unchecked")
    public static <T> T ofType(Class<?> type) {
        return (T) DEFAULT_VALUES.getOrDefault(type, null);
    }
}