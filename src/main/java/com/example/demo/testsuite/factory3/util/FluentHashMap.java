package com.example.demo.testsuite.factory3.util;

import java.util.HashMap;

public class FluentHashMap<K, V> extends HashMap<K, V> {

    public FluentHashMap<K, V> with(K key, V value) {
        put(key, value);
        return this;
    }

    public FluentHashMap<K, V> delete(K key) {
        remove(key);
        return this;
    }

}
