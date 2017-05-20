package com.corngo.base.support.cache;

import jodd.cache.LRUCache;
import org.springframework.stereotype.Component;

/**
 * Jodd实现的简单内存缓存，缓存策略为LRU(最近最少使用的先销毁)<br>
 * 仅限单机部署使用<br>
 * 集群环境下要替换实现为Redis<br>
 * Created by kfc on 2016/7/25.
 */
@Component
public class JoddCacheService implements SimpleCache {

    private LRUCacheEx<String, Object> cache = new LRUCacheEx<String, Object>(1024 * 512); // 524288个对象( 64bit JVM 初始占用内存4M ) 可根据业务需求调配

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public void put(String key, Object value) {
        cache.put(key, value);
    }

    @Override
    public void put(String key, Object value, long timeout) {
        cache.put(key, value, timeout);
    }

    @Override
    public Object get(String key) {
        return cache.get(key);
    }

    @Override
    public void remove(String key) {
        cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public boolean has(String key) {
        return cache.has(key);
    }

    class LRUCacheEx<K, V> extends LRUCache<K, V> {

        public LRUCacheEx(int cacheSize) {
            super(cacheSize);
        }

        public LRUCacheEx(int cacheSize, long timeout) {
            super(cacheSize, timeout);
        }

        public boolean has(K k) {
            return cacheMap.containsKey(k);
        }
    }
}
