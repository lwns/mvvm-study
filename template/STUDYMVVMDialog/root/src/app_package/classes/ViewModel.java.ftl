package ${packageName};

import ${appPackageName}.databinding.Dlg${activityClass}Binding;
import com.nio.so.service.steward.di.ActivityScope;
import com.nio.so.service.steward.res.base.DViewModel;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import javax.inject.Inject;

@ActivityScope
public class ${activityClass}ViewModel extends DViewModel<Dlg${activityClass}Binding>{
	
    @Inject
    public ${activityClass}ViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override public void afterViews() {
      super.afterViews();
    }

}