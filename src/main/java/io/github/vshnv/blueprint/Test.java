package io.github.vshnv.blueprint;

import com.google.gson.Gson;

import java.util.Map;

import static io.github.vshnv.blueprint.Json.*;

public class Test {
    public static void main(String[] args) {
        final JsonNode node = obj(
                name -> lit("John"),
                age -> lit(42),
                address -> obj(
                        street -> lit("Main Street"),
                        city -> lit("New York"),
                        state -> lit("NY"),
                        zip -> lit(12345)
                )
        );
        JsonNode matcher = obj(
                name -> lit("John"),
                age -> matchNumber(),
                address -> obj(
                        street -> lit("Main Street"),
                        city -> lit("New York"),
                        state -> matchString(),
                        zip -> lit(12345)
                )
        );
        final StringBuffer out = new StringBuffer();
        node.writeTo(out);
        final String json = out.toString();
        System.out.println(out);
        System.out.println("---------------");
        System.out.println("Matched");
        System.out.println("Before Match:");
        out.delete(0, out.length());
        matcher.writeTo(out);
        System.out.println(out);
        System.out.println("After Match:");
        out.delete(0, out.length());
        final int totalAttempts = 100_000;
        measureHackson(totalAttempts, json);
        measureGson(totalAttempts, json);

    }

    private static void measureGson(final int totalAttempts, final String json) {
        final Gson gson = new Gson();
        int attempts = totalAttempts;
        long nanoSum = 0;
        Map<String, Object> matcher = null;
        while (attempts-- > 0) {
            final long start = System.nanoTime();
            matcher = gson.fromJson(json, Map.class);
            final long end = System.nanoTime();
            nanoSum += end - start;
            //System.out.println("Time: " + (end - start) + " ns");
        }
        System.out.println(matcher);
        System.out.println("Gson: Average time: " + nanoSum / totalAttempts + " ns over " + totalAttempts + " attempts");

    }

    public static void measureHackson(final int totalAttempts, final String json) {
        int attempts = totalAttempts;
        long nanoSum = 0;
        JsonNode matcher = null;
        while (attempts-- > 0) {
            matcher = obj(
                    name -> matchString(),
                    age -> matchNumber(),
                    address -> obj(
                            street -> matchString(),
                            city -> matchString(),
                            state -> matchString(),
                            zip -> matchNumber()
                    )
            );
            final long start = System.nanoTime();
            matcher.match(new IndexedString(json, 0));
            final long end = System.nanoTime();
            nanoSum += end - start;
            //System.out.println("Time: " + (end - start) + " ns");
        }
        final StringBuffer out = new StringBuffer();
        matcher.writeTo(out);
        System.out.println(out);
        System.out.println("Hackson: Average time: " + nanoSum / totalAttempts + " ns over " + totalAttempts + " attempts");

    }
}
