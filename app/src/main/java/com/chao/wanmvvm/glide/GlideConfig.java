package com.chao.wanmvvm.glide;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

/**
 * Date: 2018/11/16 12:56
 * Author: hansyang
 * Description:
 */
public class GlideConfig implements GlideModule {
    public final static String TAG = "GlideConfig";

    final int MAX_HEAP_SIZE = (int)Runtime.getRuntime().maxMemory();
    final int MAX_CACHE_MEMORY_SIZE = MAX_HEAP_SIZE / 4;
    final int MAX_CACHE_DISK_SIZE = 250 * 1024 * 1024;

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, MAX_CACHE_DISK_SIZE));
        builder.setMemoryCache(new LruResourceCache(MAX_CACHE_MEMORY_SIZE));
        builder.setBitmapPool(new LruBitmapPool(MAX_CACHE_MEMORY_SIZE));
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}