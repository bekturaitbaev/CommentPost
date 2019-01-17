package com.example.post.commentpost.View;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.post.commentpost.Model.PostModel;
import com.example.post.commentpost.Presenter.PostPresenter;
import com.example.post.commentpost.R;

import java.util.ArrayList;

public class PostActivity extends AppCompatActivity {
    private ProgressBar postProgress;
    private TextView postText;
    private RecyclerView mRecyclerView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        mRecyclerView = findViewById(R.id.post_list);
        toolbar = findViewById(R.id.post_toolbar);
        setSupportActionBar(toolbar);
        mRecyclerView.setVisibility(View.GONE);
        postProgress = findViewById(R.id.post_page_progress);
        postProgress.setVisibility(View.VISIBLE);
        PostPresenter postPresenter = new PostPresenter();
        postPresenter.getPostList(this);
    }

    public void showPostList(ArrayList<PostModel> postList){
        LinearLayoutManager mlayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setLayoutManager(mlayoutManager);
        postProgress.setVisibility(View.GONE);
        final PostAdapter adapter = new PostAdapter(postList, getApplicationContext(), this);
        mRecyclerView.setAdapter(adapter);
    }
}
