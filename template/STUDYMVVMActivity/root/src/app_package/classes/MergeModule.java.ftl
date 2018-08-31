@Module public abstract class ActivityModule {

  @ActivityScope @ContributesAndroidInjector(modules = { ${activityClass}Module.class }) abstract ${activityClass}Activity bind${activityClass}Activity();
}

