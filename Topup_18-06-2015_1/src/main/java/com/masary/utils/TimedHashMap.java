/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 *
 * @author Hadi
 *
 * @param <T>
 * @param <V>
 */
public class TimedHashMap<T, V> implements Map<T, V> {

    private final Map<T, TimedValueWrapper> delegate = new ConcurrentHashMap<T, TimedValueWrapper>();
    private final long EXPIRED_TIME_IN_MILLISEC;
    private final Timer timer;

    public TimedHashMap() {
        this(5000L);
    }

    public TimedHashMap(long expiry) {
        this(expiry, 1000);
    }

    public TimedHashMap(long expiry, long interval) {
        EXPIRED_TIME_IN_MILLISEC = expiry;
        timer = new Timer();
        timer.schedule(new ClearerReminder(), 0, interval);
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return delegate.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return delegate.containsValue(value);
    }

    @Override
    public V get(Object key) {
        TimedValueWrapper w = delegate.get(key);
        if (w == null) {
            return null;
        }
        return w._value;
    }

    @Override
    public V put(T key, V value) {
        boolean found = containsKey(key);
        if (!found) {
            delegate.put(key, new TimedValueWrapper(value));
            return value;
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        TimedValueWrapper w = delegate.remove(key);
        if (w == null) {
            return null;
        }
        return w._value;
    }

    @Override
    public void putAll(Map<? extends T, ? extends V> m) {
        Set<? extends T> set = m.keySet();
        for (T t : set) {
            this.put(t, m.get(t));
        }
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public Set<T> keySet() {
        return delegate.keySet();
    }

    @Override
    public Collection<V> values() {
        Set<V> result = new HashSet<V>();
        Collection<TimedValueWrapper> wrappers = delegate.values();
        for (TimedValueWrapper t : wrappers) {
            result.add(t._value);
        }
        return result;
    }

    @Override
    public Set<java.util.Map.Entry<T, V>> entrySet() {
        Set<java.util.Map.Entry<T, V>> entries = new HashSet<java.util.Map.Entry<T, V>>();
        Set<java.util.Map.Entry<T, TimedValueWrapper>> wrappers = delegate.entrySet();
        for (Map.Entry<T, TimedValueWrapper> e : wrappers) {
            entries.add(new MutableEntry(e.getKey(), e.getValue()._value));
        }
        return entries;
    }

    /**
     *
     * @author Hadi
     *
     */
    private class ClearerReminder extends TimerTask {

        @Override
        public void run() {
            Set<java.util.Map.Entry<T, TimedValueWrapper>> wrappers = delegate.entrySet();
            Set<java.util.Map.Entry<T, TimedValueWrapper>> toBeRemoved = new HashSet<java.util.Map.Entry<T, TimedValueWrapper>>();
            for (Map.Entry<T, TimedValueWrapper> e : wrappers) {
                if ((System.currentTimeMillis() - e.getValue()._time) > EXPIRED_TIME_IN_MILLISEC) {
                    toBeRemoved.add(e);
                }
            }

            for (Map.Entry<T, TimedValueWrapper> e : toBeRemoved) {
                remove(e.getKey());
            }
        }
    }

    /**
     *
     *
     * @author Hadi
     *
     */
    private class MutableEntry implements java.util.Map.Entry<T, V> {

        private V value;
        private T key;

        public MutableEntry(T key, V value) {
            super();
            this.value = value;
            this.key = key;
        }

        @Override
        public T getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

    }

    /**
     *
     *
     * @author Hadi
     *
     */
    private class TimedValueWrapper {

        final V _value;
        final long _time;

        TimedValueWrapper(V value) {
            _value = value;
            _time = System.currentTimeMillis();
        }
    }

}
