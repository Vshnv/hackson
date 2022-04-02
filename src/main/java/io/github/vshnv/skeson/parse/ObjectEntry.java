package io.github.vshnv.skeson.parse;

import io.github.vshnv.skeson.JsonNode;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.function.Function;

import static io.github.vshnv.skeson.LambdaParameterUtils.parameterName;

public interface ObjectEntry extends Function<String, JsonNode>, Serializable {
    default String name() {
        final SerializedLambda serializedLambda = asSerialized();
        checkParametersEnabled(serializedLambda);
        return parameterName(serializedLambda, this);
    }

    default void checkParametersEnabled(final SerializedLambda serializedLambda) {
        if (Objects.equals("arg0", parameterName(serializedLambda, this))) {
            throw new IllegalStateException("You need to compile with javac -parameters for parameter reflection to work; You also need java 8u60 or newer to use it with lambdas");
        }
    }

    default SerializedLambda asSerialized() {
        try {
            Method replaceMethod = getClass().getDeclaredMethod("writeReplace");
            replaceMethod.setAccessible(true);
            return (SerializedLambda) replaceMethod.invoke(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default Class<?> implClass(final SerializedLambda serializedLambda) {
        try {
            String className = serializedLambda.getImplClass().replaceAll("/", ".");
            return Class.forName(className);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default JsonNode value() {
        return apply(name());
    }

    class UnableToGuessMethodException extends RuntimeException {}
}