package com.timper.res;

import android.content.Context;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;

@GlideModule public class AppGlide extends AppGlideModule {
  @Override public void applyOptions(Context context, GlideBuilder builder) {
    MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context).setMemoryCacheScreens(2).build();
    builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()));
  }
}
