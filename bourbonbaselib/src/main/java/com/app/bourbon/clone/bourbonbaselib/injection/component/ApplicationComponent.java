package com.app.bourbon.clone.bourbonbaselib.injection.component;

import android.app.Application;
import android.content.Context;

import com.app.bourbon.clone.bourbonbaselib.data.DataManager;
import com.app.bourbon.clone.bourbonbaselib.data.remote.BourbonService;
import com.app.bourbon.clone.bourbonbaselib.injection.ApplicationContext;
import com.app.bourbon.clone.bourbonbaselib.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by admin on 06/04/18.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    /**
     * need to fix this as currently causing this error
     * @return
     */
    @ApplicationContext
    Context context();
    Application application();
    DataManager dataManager();
    BourbonService bourbonService();

}
