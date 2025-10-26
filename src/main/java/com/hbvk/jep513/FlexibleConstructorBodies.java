package com.hbvk.jep513;

public class FlexibleConstructorBodies extends BaseClass {
    public FlexibleConstructorBodies(int count) {
        if (count < 0) throw new RuntimeException();
        super(count);
    }

    public FlexibleConstructorBodies() {
        int i = 0;
        super(i);
    }
}


