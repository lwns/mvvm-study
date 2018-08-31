package com.study.app.feature.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.study.app.R;
import com.study.app.databinding.ActMinedetailBinding;
import com.timper.lonelysword.annotations.apt.AfterViews;
import com.timper.lonelysword.annotations.apt.BeforeViews;
import com.timper.lonelysword.annotations.apt.Dagger;
import com.timper.lonelysword.annotations.apt.RootView;
import com.timper.res.base.BaseActivity;

/**
 * User:
 * Date:
 * Description:
 * FIXME
 */
@Dagger @RootView(R.layout.act_minedetail) public class MineDetailActivity
    extends BaseActivity<MineDetailViewModel, ActMinedetailBinding> {

  public final static void instance(Context context) {
    instance(context, null);
  }

  public final static void instance(Context context, Bundle bundle) {
    Intent intent = new Intent(context, MineDetailActivity.class);
    if (bundle != null) {
      intent.putExtra("data", bundle);
    }
    context.startActivity(intent);
  }

  @AfterViews void afterViews() {

  }

  @BeforeViews void beforViews() {

  }
}
