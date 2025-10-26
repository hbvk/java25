package com.hbvk.jep513;

public class BaseClass {
    int count;

    public BaseClass() {
        int i = 3;
        this(i);
    }

    public BaseClass(int count) {
        if (count < 0) throw new RuntimeException();
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
