package ${packageName};

import ${appPackageName}.R;
import ${appPackageName}.databinding.Frg${activityClass}Binding;
import com.timper.lonelysword.annotations.apt.AfterViews;
import com.timper.lonelysword.annotations.apt.BeforeViews;
import com.timper.lonelysword.annotations.apt.RootView;
import com.timper.res.base.BaseFragment;


@RootView(R.layout.frg_${activityClass?lower_case})public final class ${activityClass}Fragment extends BaseFragment<${activityClass}ViewModel,Frg${activityClass}Binding>{

    public static ${activityClass}Fragment instance() {
        return new ${activityClass}Fragment();
    }

  @BeforeViews void beforeViews() {
  }

  @AfterViews void afterViews() {
  }
}
