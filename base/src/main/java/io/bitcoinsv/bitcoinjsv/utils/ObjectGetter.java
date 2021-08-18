/*
 * Author: Steve Shadders
 * © 2020 Bitcoin Association
 * Open BSV Licence, see the accompanying file LICENSE
 */
package io.bitcoinsv.bitcoinjsv.utils;

/**
 * Simple placeholder that can return an object
 * @param <T>
 */
public interface ObjectGetter<T> {

    public T get();

    static <T> ObjectGetter<T> direct(T object) {
        return new DirectGetter<>(object);
    }

    static class DirectGetter<T> implements ObjectGetter<T> {

        private final T object;

        private DirectGetter(T object) {
            this.object = object;
        }

        @Override
        public T get() {
            return object;
        }
    }
}
