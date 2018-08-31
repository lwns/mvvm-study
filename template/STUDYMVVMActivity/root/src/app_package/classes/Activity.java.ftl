package ${packageName};

import ${appPackageName}.R;
import ${appPackageName}.databinding.Act${activityClass}Binding;
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
 @Dagger @RootView(R.layout.act_${activityClass?lower_case}) public class ${activityClass}Activity extends BaseActivity<${activityClass}ViewModel,Act${activityClass}Binding> {

   public final static void instance(Context context) {
    instance(context, null);
  }

  public final static void instance(Context context, Bundle bundle) {
    Intent intent = new Intent(context, ${activityClass}Activity.class);
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
