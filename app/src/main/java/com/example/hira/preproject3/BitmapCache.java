package com.example.hira.preproject3;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by user on 8/21/2016.
 */
public class BitmapCache extends LruCache<String,Bitmap> implements ImageLoader.ImageCache {
    public BitmapCache(int maxSize) {
        super(maxSize);
    }

    public BitmapCache() {
        this(getDefaultCachesize());
    }

    public static int getDefaultCachesize(){
        final int maxmemory=(int)(Runtime.getRuntime().maxMemory()/1204);
        final int cachesize=maxmemory/8;
        return cachesize;
    }
    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes()*value.getHeight()/1024;
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url,bitmap);
    }
}
