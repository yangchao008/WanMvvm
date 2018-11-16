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
 *
 * @author tqzhang
 */
public class GlideConfig implements GlideModule {
    public final static String TAG = "GlideConfig";

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, com.code.mvvm.config.Constants.MAX_CACHE_DISK_SIZE));
        builder.setMemoryCache(new LruResourceCache(com.code.mvvm.config.Constants.MAX_CACHE_MEMORY_SIZE));
        builder.setBitmapPool(new LruBitmapPool(com.code.mvvm.config.Constants.MAX_CACHE_MEMORY_SIZE));
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}