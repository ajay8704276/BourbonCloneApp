package com.app.bourbon.clone.bourbonbaselib.ui.browse;

import com.app.bourbon.clone.bourbonbaselib.data.DataManager;
import com.app.bourbon.clone.bourbonbaselib.data.model.Shot;
import com.app.bourbon.clone.bourbonbaselib.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import rx.SingleSubscriber;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by admin on 06/04/18.
 */

public class BrowsePresenter extends BasePresenter<BrowseMvpView> {

    public static final int SHOT_COUNT = 20;
    public static final int SHOT_PAGE = 0;


    private final DataManager mDataManager;
    private Subscription mSubscription;


    @Inject
    public BrowsePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    /**
     * Detaching view to the caller
     */
    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }


    public void getShots(int perPage, int page) {

        /**
         * Check if view attached to the  presenter or not
         *
         * if not then through exception
         *
         * Call to make sure view is attached otherwise throw exception
         */
        checkViewAttached();


        getmMvpView().showMessageLayout(false);

        getmMvpView().showProgress();

        /**
         * Need to fix this error
         *
         * for now let it be
         */
        mSubscription = mDataManager.getShots(perPage, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleSubscriber<List<Shot>>() {
                    @Override
                    public void onSuccess(List<Shot> shots) {

                        getmMvpView().hideProgress();

                        if (!shots.isEmpty()) {
                            getmMvpView().showShots(shots);
                        } else {
                            getmMvpView().showEmpty();
                        }
                    }

                    @Override
                    public void onError(Throwable error) {

                        Timber.e(error, "There was an error retrieving the shots");
                        getmMvpView().hideProgress();
                        getmMvpView().showError();
                    }
                });
    }

}
