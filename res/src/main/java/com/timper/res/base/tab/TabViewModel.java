package com.timper.res.base.tab;

import android.databinding.ObservableField;
import com.timper.lonelysword.ActivityScope;
import com.timper.lonelysword.base.AppActivity;
import com.timper.lonelysword.base.AppViewModel;
import com.timper.res.databinding.FrgTabBinding;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * User:
 * Date:
 * Description:
 * FIXME
 */
@ActivityScope public class TabViewModel extends AppViewModel<FrgTabBinding> {

  public final ObservableField<Integer> selectPosition = new ObservableField<>(0);

  public final List<String> titles = new ArrayList<>();

  public final ObservableField<Integer> pageLimit = new ObservableField<>(0);

  @Inject public TabViewModel(AppActivity activity) {
    super(activity);
  }
}