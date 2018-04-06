package com.app.bourbon.clone.bourbonbaselib;

import android.app.Application;
import android.content.Context;

import com.app.bourbon.clone.bourbonbaselib.injection.component.ApplicationComponent;
import com.app.bourbon.clone.bourbonbaselib.injection.module.ApplicationModule;

import timber.log.Timber;

/**
 * Created by admin on 06/04/18.
 */

public class BourbonApplication extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * Need to fix this dagger component initialisation
         *
         * Need to learn the dagger as it causing a lot of problems
         *
         * Please learn it and fix it
         *
         */
        if (BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree());
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static BourbonApplication get(Context context) {
        return (BourbonApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
