package com.android.kechong.lib.cache;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;

/**
 * Created by misxu012 on 2015/11/27.
 */
public class MyCache {
    private LruCache<String, Bitmap> mMemoryCache;
    private static MyCache myCache;
    private MyCache() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public static synchronized MyCache getInstance() {
        if (myCache == null) {
            myCache = new MyCache();
        }
        return myCache;
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
}
