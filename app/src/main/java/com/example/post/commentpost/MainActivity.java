package com.example.post.commentpost;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.post.commentpost.Presenter.PostPresenter;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mainPageProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PostPresenter mPostPresenter = new PostPresenter();
        mPostPresenter.goPostPage(this, getApplicationContext(), this);

    }
}
