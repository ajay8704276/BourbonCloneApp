package com.app.bourbon.clone.bourbonbaselib.ui.base;

/**
 * Created by admin on 06/04/18.
 */

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */

public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
