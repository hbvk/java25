package com.hbvk.jep485;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

import static com.hbvk.jep485.DistinctByGatherer.distinctBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

/// Tests for [JEP 485](https://openjdk.org/jeps/485): Stream Gatherers.
class Jep485GathererTest {

    @Test
    void testScanPredefinedGatherer() {
        // example from javadoc in Gatherers
        List<String> numberStrings = Stream
                .of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
                .gather(Gatherers.scan(() -> "", (string, number) -> string + number))
                .toList();
        assertEquals(
                List.of("1", "12", "123", "1234", "12345", "123456", "1234567", "12345678", "123456789", "1234567890"),
                numberStrings);
    }

    /// Implementation of an example from [JEP 485](https://openjdk.org/jeps/485).
    @Test
    void testDistinctByCustomGatherer() {
        var result = Stream.of("foo", "bar", "baz", "quux")
                .gather(distinctBy(String::length))
                .toList();
        assertEquals(2, result.size());
        assertEquals(List.of("foo", "quux"), result);
    }

}
