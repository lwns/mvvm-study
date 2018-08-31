package ${appPackageName}.di.modules;

import ${packageName}.${activityClass}Activity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import dagger.Module;
import dagger.Binds;

/**
 * User: 
 * Date: 
 * Description:
 * FIXME
 */
@Module public abstract class ${activityClass}Module {

  @Binds public abstract RxAppCompatActivity provide${activityClass}View(${activityClass}Activity ${activityClass?lower_case}Activity);
  
//copy the line code to ActivityModule.java
//  @ActivityScope @ContributesAndroidInjector(modules = { ${activityClass}Module.class,${activityClass}SubModule.class }) abstract ${activityClass}Activity bind${activityClass}Activity();
}
