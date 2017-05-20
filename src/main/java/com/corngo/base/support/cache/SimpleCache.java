package com.corngo.base.support.cache;

/**
 * Created by kfc on 2016/7/25.
 */
public interface SimpleCache {

    int size();

    void put(String key, Object value);

    void put(String key, Object value, long timeout);

    Object get(String key);

    void remove(String key);

    void clear();

    boolean has(String key);
}
