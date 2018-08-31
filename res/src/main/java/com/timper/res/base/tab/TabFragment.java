package com.timper.res.base.tab;

import android.support.v4.app.Fragment;
import com.timper.res.databinding.FrgTabBinding;
import com.timper.lonelysword.base.AppFragment;
import java.util.ArrayList;
import java.util.List;

public abstract class TabFragment<V extends TabViewModel, T extends FrgTabBinding> extends AppFragment<V, T> {

  public final List<Fragment> fragments = new ArrayList<>();
}
