package com.alibaba.alava.collections;

import junit.framework.TestCase;

import static org.junit.Assert.*;

public class IntIntArrayMapTestTest extends TestCase {
    public void test_getAndPut() throws Exception {
        IntIntMap map = new IntIntMap(16);

        for (int i = 1; i <= 16 * 3; i+= 3) {
            map.put(i, i * 2);
        }

        for (int i = 1; i <= 16 * 3; i+=3) {
            assertEquals(i * 2, map.get(i));
        }

        map.put(2, 102);
        assertEquals(102, map.get(2));
    }
}