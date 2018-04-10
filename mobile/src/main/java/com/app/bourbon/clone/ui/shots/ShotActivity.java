package com.app.bourbon.clone.ui.shots;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.app.bourbon.clone.R;
import com.app.bourbon.clone.bourbonbaselib.data.model.Shot;
import com.app.bourbon.clone.ui.base.BaseActivity;

/**
 * Created by ajay on 9/4/18.
 */

public class ShotActivity extends BaseActivity {


    public static final String EXTRA_SHOT = "com.app.bourbon.clone.ui.shots.EXTRA_SHOT";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Shot shot = getIntent().getParcelableExtra(EXTRA_SHOT);
        if(shot == null){
            throw new IllegalArgumentException("Shot activity requires shot instance!");

        }

        getActivityComponent().inject(this);
        setContentView(R.layout.shot_activity);

        ShotFragment shotFragment = ShotFragment.newInstance(shot);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container,shotFragment)
                .commit();
    }
}
