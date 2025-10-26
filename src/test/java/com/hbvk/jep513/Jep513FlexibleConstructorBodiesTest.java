package com.hbvk.jep513;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Jep513FlexibleConstructorBodiesTest {

    @Test
    void testStatementsBeforeSuper() {
        FlexibleConstructorBodies flexibleConstructorBodies = new FlexibleConstructorBodies();
        assertEquals(0, flexibleConstructorBodies.getCount());
    }

    @Test
    void testStatementsBeforeThis() {
        BaseClass baseClass = new BaseClass();
        assertEquals(3, baseClass.getCount());
    }
}
