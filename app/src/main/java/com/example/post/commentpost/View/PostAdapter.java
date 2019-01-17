package com.example.post.commentpost.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.post.commentpost.Model.PostModel;
import com.example.post.commentpost.Presenter.CommentPresenter;
import com.example.post.commentpost.R;


import java.util.ArrayList;



public class PostAdapter extends RecyclerView.Adapter{

    private ArrayList<PostModel> postList = new ArrayList<>();
    private Context mContext;
    private Activity activity;
    private final int VIEW_ITEM = 0, VIEW_LOADING = 1;

    public PostAdapter(ArrayList<PostModel> postList, Context mContext, Activity activity) {
        this.postList = postList;
        this.mContext = mContext;
        this.activity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        return postList.get(position) != null ? VIEW_ITEM : VIEW_LOADING;    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.post_single_item, parent, false);

        vh = new PostItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder mHolder, final int position) {
        mHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentPresenter commentPresenter = new CommentPresenter();
                commentPresenter.postClicked(postList.get(position).getPostId(), mContext, activity);
            }
        });

        if (mHolder instanceof PostItemViewHolder) {

            ((PostItemViewHolder) mHolder).postTitle.setText(postList.get(position).getTitle());
            ((PostItemViewHolder) mHolder).postBody.setText(postList.get(position).getBody());
//            ((ChatItemViewHolder) holder).chatStadiumDate.setText(stadiumChatDate.get(position));

        }

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

}

class PostItemViewHolder extends RecyclerView.ViewHolder{
    TextView postTitle;
    TextView postBody;
    public PostItemViewHolder(View itemView){
        super(itemView);
        postTitle = itemView.findViewById(R.id.post_title_text);
        postBody = itemView.findViewById(R.id.post_body_text);
    }
}