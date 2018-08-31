package com.timper.res.utils;

import android.content.Context;
import android.content.res.Resources;

/**
 * @author : tangpeng.yang.o(tangpeng.yang.o@nio.com)
 * @Date: 2017-09-28 20:24
 * Description:
 * FIXME
 */

public class ResUtil {

    public static int getResourceId(Context context, String name) {
        Resources res = context.getResources();
        final String packageName = context.getPackageName();
        int imageResId = res.getIdentifier(name, "mipmap", packageName);
        return imageResId;
    }

}
