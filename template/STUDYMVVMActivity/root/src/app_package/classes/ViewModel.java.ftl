package ${packageName};

import ${appPackageName}.databinding.Act${activityClass}Binding;
import com.timper.lonelysword.ActivityScope;
import com.timper.lonelysword.base.AppActivity;
import com.timper.lonelysword.base.AppViewModel;
<#if hasModelAdater!false>
import com.timper.res.base.ModelAdapterFactor;
import android.arch.lifecycle.ViewModelProviders;</#if>
import javax.inject.Inject;

/**
 * User: 
 * Date: 
 * Description:
 * FIXME
 */
@ActivityScope
public class ${activityClass}ViewModel extends AppViewModel<Act${activityClass}Binding>{

    <#if hasModelAdater!false>${activityClass}ModelAdapter ${activityClass?lower_case}ModelAdapter;</#if>

    @Inject
    public ${activityClass}ViewModel(AppActivity activity <#if hasModelAdater!false>,ModelAdapterFactor<${activityClass}ModelAdapter> factor</#if>) {
        super(activity);
        <#if hasModelAdater!false>this.${activityClass?lower_case}ModelAdapter = ViewModelProviders.of(activity, factor).get(${activityClass}ModelAdapter.class);</#if>
    }

   @Override public void afterViews() {
    super.afterViews();
  }
}