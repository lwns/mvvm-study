package ${packageName};


import com.timper.lonelysword.base.AppActivity;
import com.timper.res.base.BaseViewModel;
import com.timper.lonelysword.ActivityScope;
import ${appPackageName}.databinding.Frg${activityClass}Binding;
<#if hasModelAdater!false>import ${appPackageName}.feature.${attachtoactivityClass?lower_case}.${attachtoactivityClass}ModelAdapter;
import com.timper.res.base.ModelAdapterFactor;
import android.arch.lifecycle.ViewModelProviders;</#if>

import javax.inject.Inject;


@ActivityScope
public class ${activityClass}ViewModel extends BaseViewModel<Frg${activityClass}Binding>{

    <#if hasModelAdater!false>${attachtoactivityClass}ModelAdapter ${attachtoactivityClass?lower_case}ModelAdapter;</#if>

    @Inject
    public ${activityClass}ViewModel(AppActivity activity <#if hasModelAdater!false>,ModelAdapterFactor<${attachtoactivityClass}ModelAdapter> factor</#if>) {
        super(activity);
          <#if hasModelAdater!false>this.${attachtoactivityClass?lower_case}ModelAdapter = ViewModelProviders.of(activity, factor).get(${attachtoactivityClass}ModelAdapter.class);</#if>
    }

    @Override
    public void afterViews() {
        super.afterViews();
    }
}