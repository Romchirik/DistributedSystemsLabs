package ru.nsu.titov.app;

import lombok.Getter;

public class KeyValuePair<K, V> {

    @Getter
    private final K key;

    @Getter
    private final V value;

    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
