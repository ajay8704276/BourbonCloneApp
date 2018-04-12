package com.app.bourbon.clone.bourbonbaselib.injection.component;

import com.app.bourbon.clone.bourbonbaselib.injection.module.ApplicationTestModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by admin on 11/04/18.
 */
@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {
}
