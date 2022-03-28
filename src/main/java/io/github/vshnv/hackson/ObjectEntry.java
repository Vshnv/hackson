package io.github.vshnv.hackson;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.function.Function;

import static io.github.vshnv.hackson.LambdaParameterUtils.parameterName;

public interface ObjectEntry extends Function<Void, JsonNode>, Serializable {
    default String name() {
        checkParametersEnabled();
        return parameterName(this, 0);
    }

    default void checkParametersEnabled() {
        if (Objects.equals("arg0", parameterName(this, 0))) {
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

    default Class<?> implClass() {
        try {
            String className = asSerialized().getImplClass().replaceAll("/", ".");
            return Class.forName(className);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default JsonNode value() {
        return apply(null);
    }

    class UnableToGuessMethodException extends RuntimeException {}
}