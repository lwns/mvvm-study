package com.timper.res.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import com.timper.res.utils.permission.PermissionListener;
import com.timper.res.utils.permission.VomPermission;
import java.util.List;

public class IntentUtil {

  public static void callPhone(final Context context, String phoneNum) {
    if (StrUtil.isEmpty(phoneNum)) {
      return;
    }

    final Intent intent = new Intent(Intent.ACTION_CALL);
    Uri data = Uri.parse("tel:" + phoneNum);
    intent.setData(data);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    if (context != null) {
      VomPermission.with(context).requestCode(100).permission(Manifest.permission.CALL_PHONE).callback(new PermissionListener() {
        @SuppressLint("MissingPermission") @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
          context.startActivity(intent);
        }

        @Override public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
        }
      }).start();
    }
  }
}
