package com.app.bourbon.clone.bourbonbaselib.ui.shots;

import com.app.bourbon.clone.bourbonbaselib.data.model.Comment;
import com.app.bourbon.clone.bourbonbaselib.ui.base.MvpView;

import java.util.List;

/**
 * Created by admin on 06/04/18.
 */

public interface ShotsMvpView extends MvpView {

    void showProgress();


    void hideProgress();


    void showComments(List<Comment> comments);


    void showError();


    void showEmptyComments();


    void showCommentsTitle(boolean hasComments);
}
