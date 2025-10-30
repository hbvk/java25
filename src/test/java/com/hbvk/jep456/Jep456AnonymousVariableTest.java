package com.hbvk.jep456;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/// A few examples of unnamed (or anonymous) variables, a long overdue enhancement in Java 22.
class Jep456AnonymousVariableTest {

    /// An unnamed loop variable.
    @Test
    void loopVariable() {
        int total = 0;
        int[] numbers = {1, 2, 3, 4, 5};
        for (var _ : numbers)
            total++;
        assertEquals(5, total);
    }

    /// `catch` clause with an unnamed exception variable
    @Test
    void exception() {
        try {
            Integer.parseInt("");
            fail();
        } catch (NumberFormatException _) {
            // ignore
        }
    }

    /// Example of an anonymous `switch` pattern.
    @Test
    void switchPatternVariables() {
        Object o = this;
        switch (o) {
            case Jep456AnonymousVariableTest _: {
                break;
            }
            case Object _: {
                // Yup, we'll never get here. Just showing that you can use anonymous switch pattern variables ;-)
                fail();
            }
        }
    }

    /// try-with-resources with an anonymous resource.
    ///
    /// @throws IOException because we do file I/O
    @Test
    void tryWithResources() throws IOException {
        FileReader fileReader = new FileReader("./pom.xml");
        try (var _ = new BufferedReader(fileReader)) {
            assertTrue(fileReader.ready());
        }
    }

    /// Example of an unnamed lambda variable.
    @Test
    void lambdaParameter() {
        Map<String, String> map = new HashMap<>();
        map.computeIfAbsent("key", _ -> null);
        assertTrue(map.isEmpty());
    }
}
