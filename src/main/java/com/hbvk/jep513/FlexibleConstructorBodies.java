package com.hbvk.jep513;

/// Code before `super()` is possible since Java 25. Both constructors of this class do some
/// initialization or validation before calling `super()`.
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


