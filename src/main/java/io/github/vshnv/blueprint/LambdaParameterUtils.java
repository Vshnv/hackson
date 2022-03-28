package io.github.vshnv.blueprint;

import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.Objects;

public final class LambdaParameterUtils {
    private LambdaParameterUtils() {
        throw new AssertionError("no instances.");
    }

    public static String parameterName(final ObjectEntry entry, final int index) {
        final SerializedLambda lambda = entry.asSerialized();
        final Class<?> containingClass = entry.implClass();
        return Arrays.stream(containingClass.getDeclaredMethods())
                .filter(method -> Objects.equals(method.getName(), lambda.getImplMethodName()))
                .findFirst()
                .orElseThrow(ObjectEntry.UnableToGuessMethodException::new)
                .getParameters()[index].getName();
    }
}
