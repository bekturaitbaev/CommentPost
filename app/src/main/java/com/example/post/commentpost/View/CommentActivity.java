package com.example.post.commentpost.View;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.support.v7.widget.Toolbar;

import com.example.post.commentpost.Model.CommentModel;
import com.example.post.commentpost.Model.PostModel;
import com.example.post.commentpost.Presenter.CommentPresenter;
import com.example.post.commentpost.R;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {
    private ProgressBar commentProgress;
    private ArrayList<CommentModel> listComment = new ArrayList<>();
    private Toolbar commentToolbar;
    CommentAdapter adapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        commentProgress = findViewById(R.id.comment_page_progress);
        commentProgress.setVisibility(View.VISIBLE);
        commentToolbar = findViewById(R.id.comment_toolbar);
        setSupportActionBar(commentToolbar);
        mRecyclerView = findViewById(R.id.comment_list);
        mRecyclerView.setVisibility(View.GONE);
        CommentPresenter commentPresenter = new CommentPresenter();
        commentPresenter.getComments(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_comment) {
            final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog, null);

            final EditText nameText = (EditText) dialogView.findViewById(R.id.name_text);
            final EditText emailText = (EditText) dialogView.findViewById(R.id.email_text);
            final EditText commentText = (EditText) dialogView.findViewById(R.id.comment_text);
            Button submitBtn = (Button) dialogView.findViewById(R.id.buttonSubmit);
            Button cancelBtn = (Button) dialogView.findViewById(R.id.buttonCancel);

            submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listComment.add(new CommentModel("1", "1", nameText.getText().toString(), emailText.getText().toString(), commentText.getText().toString()));
                    adapter.notifyDataSetChanged();

                    dialogBuilder.dismiss();
                }
            });
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // DO SOMETHINGS
                    dialogBuilder.dismiss();
                }
            });

            dialogBuilder.setView(dialogView);
            dialogBuilder.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.comment_menu, menu);
        return true;
    }

    public void showCommentList(ArrayList<CommentModel> commentList){
        listComment = commentList;
        LinearLayoutManager mlayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setLayoutManager(mlayoutManager);
        commentProgress.setVisibility(View.GONE);
        adapter = new CommentAdapter(commentList, getApplicationContext());
        mRecyclerView.setAdapter(adapter);
    }


}
