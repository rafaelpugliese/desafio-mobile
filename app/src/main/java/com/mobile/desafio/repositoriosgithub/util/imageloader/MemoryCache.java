package com.mobile.desafio.repositoriosgithub.util.imageloader;

import android.graphics.Bitmap;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MemoryCache {

    private static MemoryCache memoryCache;

    static MemoryCache getInstance() {
        if (memoryCache == null) {
            memoryCache = new MemoryCache();
        }
        return memoryCache;
    }

    private Map<String, SoftReference<Bitmap>> cache = Collections.synchronizedMap(new HashMap<String, SoftReference<Bitmap>>());

    public Bitmap get(String id) {
        if (!cache.containsKey(id)) {
            return null;
        }
        SoftReference<Bitmap> ref = cache.get(id);
        return ref.get();
    }

    void put(String id, Bitmap bitmap) {
        cache.put(id, new SoftReference<>(bitmap));
    }

    void clear() {
        cache.clear();
    }

}