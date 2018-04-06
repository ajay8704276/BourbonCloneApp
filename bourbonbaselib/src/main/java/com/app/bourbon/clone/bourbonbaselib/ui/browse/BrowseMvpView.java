package com.app.bourbon.clone.bourbonbaselib.ui.browse;

import com.app.bourbon.clone.bourbonbaselib.data.model.Shot;
import com.app.bourbon.clone.bourbonbaselib.ui.base.MvpView;

import java.util.List;

/**
 * Created by admin on 06/04/18.
 */

public interface BrowseMvpView extends MvpView {


    void showProgress();

    void hideProgress();

    void showShots(List<Shot> shots);

    void showError();

    void showEmpty();

    void showMessageLayout(boolean show);

}
