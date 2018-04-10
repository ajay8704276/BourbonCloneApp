package com.app.bourbon.clone.ui.shots;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.bourbon.clone.R;
import com.app.bourbon.clone.bourbonbaselib.data.model.Comment;
import com.app.bourbon.clone.bourbonbaselib.data.model.Shot;
import com.app.bourbon.clone.bourbonbaselib.ui.shots.ShotsMvpView;
import com.app.bourbon.clone.bourbonbaselib.ui.shots.ShotsPresenter;
import com.app.bourbon.clone.ui.base.BaseActivity;
import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;


/**
 * Created by ajay on 9/4/18.
 */

public class ShotFragment extends Fragment implements ShotsMvpView {

    private static final String ARGUMENT_SHOT = "ARGUMENT_SHOT";

    @Inject
    CommentAdapter commentAdapter;
    @Inject
    ShotsPresenter shotsPresenter;

    private RecyclerView mCommentsRecycler;
    private Toolbar mToolbar;
    private ImageView mShotImage;
    private TextView mTitleText, mLikeText, mCommentsTitleText;
    private View mProgress;
    private View mErrorText;


    public static ShotFragment newInstance(Shot shot) {
        ShotFragment shotFragment = new ShotFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGUMENT_SHOT, shot);
        shotFragment.setArguments(bundle);
        return shotFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((BaseActivity) getActivity()).getActivityComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_shot, container, false);

        mCommentsRecycler = fragmentView.findViewById(R.id.recycler_comments);
        mToolbar = fragmentView.findViewById(R.id.toolbar_shot);
        mShotImage = fragmentView.findViewById(R.id.image_shot);
        mTitleText = fragmentView.findViewById(R.id.text_title);
        mLikeText = fragmentView.findViewById(R.id.text_like_count);
        mCommentsTitleText = fragmentView.findViewById(R.id.text_comments_title);
        mProgress = fragmentView.findViewById(R.id.progress);
        mErrorText = fragmentView.findViewById(R.id.text_error_message);


        Shot shot = getArguments().getParcelable(ARGUMENT_SHOT);

        if (shot == null) {
            throw new IllegalArgumentException("Shotfragment requires shot instance");
        }

        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        shotsPresenter.attachView(this);

        mCommentsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCommentsRecycler.setHasFixedSize(true);
        mCommentsRecycler.setAdapter(commentAdapter);


        Glide.with(this).load(shot.images.normal)
                .into(mShotImage);
        mTitleText.setText(shot.title);
        mLikeText.setText(shot.likes_count);

        shotsPresenter.getComments(shot.id, ShotsPresenter.SHOT_COUNT, ShotsPresenter.SHOT_PAGE);


        return fragmentView;
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void showComments(List<Comment> comments) {
        commentAdapter.setComments(comments);
        commentAdapter.notifyDataSetChanged();
        mCommentsRecycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        mCommentsRecycler.setVisibility(View.GONE);
        mCommentsTitleText.setVisibility(View.GONE);
        mErrorText.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyComments() {
        mCommentsTitleText.setVisibility(View.VISIBLE);
        mCommentsRecycler.setVisibility(View.GONE);
    }

    @Override
    public void showCommentsTitle(boolean hasComments) {
        mCommentsTitleText.setText(getString(hasComments ?
                R.string.text_recent_comments : R.string.text_no_recent_comments));
        mCommentsTitleText.setVisibility(View.VISIBLE);
    }
}
