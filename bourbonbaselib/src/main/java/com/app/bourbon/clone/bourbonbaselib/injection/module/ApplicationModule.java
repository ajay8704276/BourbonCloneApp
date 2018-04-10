package com.app.bourbon.clone.bourbonbaselib.injection.module;

import android.app.Application;
import android.content.Context;

import com.app.bourbon.clone.bourbonbaselib.data.remote.BourbonService;
import com.app.bourbon.clone.bourbonbaselib.data.remote.BourbonServiceFactory;
import com.app.bourbon.clone.bourbonbaselib.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 06/04/18.
 */
@Module
public class ApplicationModule {

    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    BourbonService provideBourbonService() {
        return BourbonServiceFactory.makeBourbonService();
    }
}
