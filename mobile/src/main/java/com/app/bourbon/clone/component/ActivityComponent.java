package com.app.bourbon.clone.component;

/**
 * Created by admin on 09/04/18.
 */

import com.app.bourbon.clone.bourbonbaselib.injection.PerActivity;
import com.app.bourbon.clone.bourbonbaselib.injection.component.ApplicationComponent;
import com.app.bourbon.clone.bourbonbaselib.injection.module.ActivityModule;
import com.app.bourbon.clone.ui.browse.BrowseFragment;
import com.app.bourbon.clone.ui.shots.ShotActivity;
import com.app.bourbon.clone.ui.shots.ShotFragment;

import dagger.Component;

/**
 * This component inject dependencies to all activities across the module
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(BrowseFragment mMBrowseFragment);

    void inject(ShotFragment mShotFragment);

    void inject(ShotActivity mShotActivity);
}
