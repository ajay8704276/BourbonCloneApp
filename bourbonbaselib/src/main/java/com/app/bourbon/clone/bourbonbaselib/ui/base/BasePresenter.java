package com.app.bourbon.clone.bourbonbaselib.ui.base;

/**
 * Created by admin on 06/04/18.
 */

public class BasePresenter<T extends MvpView> implements Presenter<T> {

    public T mMvpView;

    /**
     * attaching view to the caller
     * @param mvpView
     */
    @Override
    public void attachView(T mvpView) {

        mMvpView = mvpView;
    }

    /**
     * Detaching view to the caller
     */
    @Override
    public void detachView() {

        mMvpView = null;
    }


    /**
     * Check view is attached to the view
     *
     * if connected return the view
     *  else throw exception
     * @return
     */
    public boolean isViewAttached(){
        return mMvpView!=null;
    }

    /**
     * Return the view to the caller
     */
    public T getmMvpView(){
        return mMvpView;
    }


    /**
     * Check view is attached or not otherwise through exception
     */
    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
