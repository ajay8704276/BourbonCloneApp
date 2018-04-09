package com.app.bourbon.clone.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.app.bourbon.clone.bourbonbaselib.BourbonApplication;
import com.app.bourbon.clone.bourbonbaselib.injection.module.ActivityModule;
import com.app.bourbon.clone.component.ActivityComponent;

/**
 * Created by ajay on 9/4/18.
 */

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public ActivityComponent getActivityComponent(){
        if (activityComponent == null){
            activityComponent =  DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule())
                    .applicationComponent(BourbonApplication.get(this).getComponent())
                    .build();
        }
    }
}