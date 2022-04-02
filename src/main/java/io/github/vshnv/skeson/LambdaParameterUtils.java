package io.github.vshnv.skeson;

import io.github.vshnv.skeson.parse.ObjectEntry;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Objects;

public final class LambdaParameterUtils {
    private LambdaParameterUtils() {
        throw new AssertionError("no instances.");
    }

    public static String parameterName(final SerializedLambda lambda, final ObjectEntry entry) {
        final Class<?> containingClass = entry.implClass(lambda);
        final Parameter[] parameters = Arrays.stream(containingClass.getDeclaredMethods())
                .filter(method -> Objects.equals(method.getName(), lambda.getImplMethodName()))
                .findFirst()
                .orElseThrow(ObjectEntry.UnableToGuessMethodException::new)
                .getParameters();
        return parameters[parameters.length - 1].getName();
    }
}
