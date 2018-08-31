package ${packageName};

import android.view.Gravity;
import ${appPackageName}.R;
import ${appPackageName}.BR;
import ${appPackageName}.databinding.Dlg${activityClass}Binding;
import com.nio.so.service.steward.di.ActivityScope;
import com.nio.so.service.steward.res.annotation.AfterViews;
import com.nio.so.service.steward.res.base.BDialog;
import com.nio.so.service.steward.res.bindingadapter.collection.ItemBinding;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import javax.inject.Inject;

@ActivityScope public final class ${activityClass}Dialog extends BDialog<${activityClass}ViewModel,Dlg${activityClass}Binding>{

    @Override
    public ItemBinding rootView() {
        return ItemBinding.of(BR.viewModel, R.layout.dlg_${activityClass?lower_case});
    }

    @Inject
    public ${activityClass}Dialog(RxAppCompatActivity activity, ${activityClass}ViewModel viewModel) {
        super(BDialog.newDialog(activity).setGravity(Gravity.BOTTOM).setMargin(0, 0, 0, 0), viewModel);
    }

    @AfterViews
    void afterViews() {
    }
}
