package com.app.bourbon.clone.component;

import com.app.bourbon.clone.bourbonbaselib.injection.PerActivity;
import com.app.bourbon.clone.bourbonbaselib.injection.component.ApplicationComponent;
import com.app.bourbon.clone.bourbonbaselib.injection.module.ActivityModule;
import com.app.bourbon.clone.ui.browse.BrowseFragment;
import com.app.bourbon.clone.ui.shots.ShotActivity;
import com.app.bourbon.clone.ui.shots.ShotFragment;

import dagger.Component;

/**
 * Created by ajay on 9/4/18.
 *
 * This interface is created to inject the dependencies on all activities
 *
 * across the application
 *
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {


    void inject(BrowseFragment browseFragment);

    void inject(ShotFragment shotFragment);

    void inject(ShotActivity shotActivity);
}
