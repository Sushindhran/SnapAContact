/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.infy.snapacontact.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class LruCache.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class LruCache<K, V> {

    /** The m lru map. */
    private final HashMap<K, V> mLruMap;
    
    /** The m weak map. */
    private final HashMap<K, Entry<K, V>> mWeakMap =
            new HashMap<K, Entry<K, V>>();
    
    /** The m queue. */
    private ReferenceQueue<V> mQueue = new ReferenceQueue<V>();

    /**
     * Instantiates a new lru cache.
     *
     * @param capacity the capacity
     */
    public LruCache(final int capacity) {
        mLruMap = new LinkedHashMap<K, V>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    /**
     * The Class Entry.
     *
     * @param <K> the key type
     * @param <V> the value type
     */
    private static class Entry<K, V> extends WeakReference<V> {
        
        /** The m key. */
        K mKey;

        /**
         * Instantiates a new entry.
         *
         * @param key the key
         * @param value the value
         * @param queue the queue
         */
        public Entry(K key, V value, ReferenceQueue queue) {
            super(value, queue);
            mKey = key;
        }
    }

    /**
     * Clean up weak map.
     */
    private void cleanUpWeakMap() {
        Entry<K, V> entry = (Entry<K, V>) mQueue.poll();
        while (entry != null) {
            mWeakMap.remove(entry.mKey);
            entry = (Entry<K, V>) mQueue.poll();
        }
    }

    /**
     * Put.
     *
     * @param key the key
     * @param value the value
     * @return the v
     */
    public synchronized V put(K key, V value) {
        cleanUpWeakMap();
        mLruMap.put(key, value);
        Entry<K, V> entry = mWeakMap.put(
                key, new Entry<K, V>(key, value, mQueue));
        return entry == null ? null : entry.get();
    }

    /**
     * Gets the.
     *
     * @param key the key
     * @return the v
     */
    public synchronized V get(K key) {
        cleanUpWeakMap();
        V value = mLruMap.get(key);
        if (value != null) return value;
        Entry<K, V> entry = mWeakMap.get(key);
        return entry == null ? null : entry.get();
    }

    /**
     * Clear.
     */
    public synchronized void clear() {
        mLruMap.clear();
        mWeakMap.clear();
        mQueue = new ReferenceQueue<V>();
    }
}
