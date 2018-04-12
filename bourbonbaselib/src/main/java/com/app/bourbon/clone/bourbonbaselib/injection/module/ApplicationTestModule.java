package com.app.bourbon.clone.bourbonbaselib.injection.module;

import android.app.Application;
import android.content.Context;

import com.app.bourbon.clone.bourbonbaselib.data.DataManager;
import com.app.bourbon.clone.bourbonbaselib.data.remote.BourbonService;
import com.app.bourbon.clone.bourbonbaselib.injection.ApplicationContext;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 11/04/18.
 */

@Module
public class ApplicationTestModule {

    private final Application mApplication;

    public ApplicationTestModule(Application application) {
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

    /************* MOCKS *************/

    @Provides
    @Singleton
    DataManager providesDataManager() {
        return Mockito.mock(DataManager.class);
    }

    @Provides
    @Singleton
    BourbonService provideBourbonService() {
        return Mockito.mock(BourbonService.class);
    }
}
