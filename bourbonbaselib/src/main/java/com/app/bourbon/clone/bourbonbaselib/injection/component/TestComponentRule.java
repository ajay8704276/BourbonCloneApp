package com.app.bourbon.clone.bourbonbaselib.injection.component;

import android.content.Context;

import com.app.bourbon.clone.bourbonbaselib.BourbonApplication;
import com.app.bourbon.clone.bourbonbaselib.data.DataManager;
import com.app.bourbon.clone.bourbonbaselib.injection.module.ApplicationTestModule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by admin on 11/04/18.
 */

public class TestComponentRule implements TestRule {
    private final TestComponent mTestComponent;
    private final Context mContext;

    public TestComponentRule(Context context) {
        mContext = context;
        BourbonApplication application = BourbonApplication.get(context);
        mTestComponent = DaggerTestComponent.builder()
                .applicationTestModule(new ApplicationTestModule(application))
                .build();
    }

    public TestComponent getTestComponent() {
        return mTestComponent;
    }

    public Context getContext() {
        return mContext;
    }

    public DataManager getMockDataManager() {
        return mTestComponent.dataManager();
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                BourbonApplication application = BourbonApplication.get(mContext);
                application.setComponent(mTestComponent);
                base.evaluate();
                application.setComponent(null);
            }
        };
    }
}
