package com.app.bourbon.clone.bourbonbaselib.ui.shots;

import com.app.bourbon.clone.bourbonbaselib.data.DataManager;
import com.app.bourbon.clone.bourbonbaselib.data.model.Comment;
import com.app.bourbon.clone.bourbonbaselib.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import rx.SingleSubscriber;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by admin on 06/04/18.
 */

public class ShotsPresenter extends BasePresenter<ShotsMvpView> {

    // We'll handle pagination in the future...
    public static final int SHOT_COUNT = 10;
    public static final int SHOT_PAGE = 0;

    private final DataManager mDataManager;
    private Subscription mSubscription;
    private List<Comment> mComments;

    @Inject
    public ShotsPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void getComments(int id, int perPage, int page) {
        checkViewAttached();
        getmMvpView().showProgress();

        Single<List<Comment>> single;
        if (mComments == null) {
            single = mDataManager.getComments(id, perPage, page);
        } else {
            single = Single.just(mComments);
        }

        /**
         * Need to fix this error
         *
         * For as of now leave it
         *
         * fix after reading Rx Java and Rx Android
         */
        mSubscription = single
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleSubscriber<List<Comment>>() {
                    @Override
                    public void onSuccess(List<Comment> comments) {
                        mComments = comments;
                        getmMvpView().hideProgress();
                        if (comments.isEmpty()) {
                            getmMvpView().showEmptyComments();
                        } else {
                            getmMvpView().showComments(comments);
                        }
                        getmMvpView().showCommentsTitle(!comments.isEmpty());
                    }

                    @Override
                    public void onError(Throwable error) {
                        Timber.e(error, "There was an error retrieving the comments");
                        getmMvpView().hideProgress();
                        getmMvpView().showError();
                    }
                });

    }
}
