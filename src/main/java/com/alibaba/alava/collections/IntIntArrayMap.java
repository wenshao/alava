package com.alibaba.alava.collections;

import java.util.Arrays;

public class IntIntArrayMap {
    public static final int EMPTY_KEY = 0;

    private long[] a;

    private int size;

    private transient boolean found = false;

    public IntIntArrayMap(int initialCapacity) {
        this.a = new long[initialCapacity];
    }

    public int get(int key) {
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            long kv = a[mid];
            int k = key(kv);

            if (k < key) {
                low = mid + 1;
            } else if (k > key) {
                high = mid - 1;
            } else { // key found
                int v = val(kv);
                found = true;
                return v;
            }
        }

        found = false;
        return EMPTY_KEY;
    }

    public int put(int key, int value) {
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            long kv = a[mid];
            int k = key(kv);

            if (k < key) {
                low = mid + 1;
            } else if (k > key) {
                high = mid - 1;
            } else { // key found
                int v = val(kv);
                long kv2 = kv(key, value);
                a[mid] = kv2;
                return v;
            }
        }

        int index = low;
        if (size == a.length) {
            int newCapacity = size + 1;
            grow(newCapacity);
            System.arraycopy(a, index, a, index + 1, size - index);
        }

        a[index] = kv(key, value);
        size++;
        return EMPTY_KEY;
    }

    public boolean wasFound() {
        return found;
    }

    public int size() {
        return this.size;
    }

    private void grow(int minCapacity) {
        int oldCapacity = a.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        a = Arrays.copyOf(a, newCapacity);
    }

    private static long kv(int key, int value) {
        return (((long) key) << 32) + (long)value;
    }

    private static int key(long kv) {
        return (int) (kv >>> 32);
    }

    private static int val(long kv) {
        return (int) kv;
    }
}
