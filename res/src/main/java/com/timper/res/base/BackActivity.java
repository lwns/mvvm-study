package com.timper.res.base;

import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import com.timper.lonelysword.base.AppViewModel;
import com.timper.res.R;

/**
 * User: tangpeng.yang
 * Date: 25/07/2018
 * Description:
 * FIXME
 */
public class BackActivity<V extends AppViewModel, T extends ViewDataBinding> extends BaseActivity<V, T> {

  protected Toolbar toolbar;

  @Override protected void initWindow() {
    super.initWindow();
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    if (toolbar != null) {
      setSupportActionBar(toolbar);
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null) {
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(false);
      }
    }
  }

  @SuppressWarnings("ConstantConditions") protected void setDarkToolBar() {
    if (toolbar != null) {
      toolbar.setTitleTextColor(Color.BLACK);
      DrawableCompat.setTint(toolbar.getNavigationIcon(), Color.BLACK);
    }
  }

  @Override public boolean onSupportNavigateUp() {
    finish();
    return super.onSupportNavigateUp();
  }
}
