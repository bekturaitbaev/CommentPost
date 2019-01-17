package com.example.post.commentpost.View;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.post.commentpost.Model.CommentModel;
import com.example.post.commentpost.Model.PostModel;
import com.example.post.commentpost.Presenter.CommentPresenter;
import com.example.post.commentpost.R;


import java.util.ArrayList;



public class CommentAdapter extends RecyclerView.Adapter{

    private ArrayList<CommentModel> commentList = new ArrayList<>();
    private Context mContext;
    private final int VIEW_ITEM = 0, VIEW_LOADING = 1;

    public CommentAdapter(ArrayList<CommentModel> commentList, Context mContext) {
        this.commentList = commentList;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        return commentList.get(position) != null ? VIEW_ITEM : VIEW_LOADING;    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.comment_single_item, parent, false);

        vh = new CommentItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder mHolder, final int position) {

        if (mHolder instanceof CommentItemViewHolder) {

            ((CommentItemViewHolder) mHolder).commentName.setText(commentList.get(position).getName());
            ((CommentItemViewHolder) mHolder).commentEmail.setText(commentList.get(position).getEmail());
            ((CommentItemViewHolder) mHolder).commentBody.setText(commentList.get(position).getBody());

        }

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

}

class CommentItemViewHolder extends RecyclerView.ViewHolder{
    TextView commentName;
    TextView commentEmail;
    TextView commentBody;
    public CommentItemViewHolder(View itemView){
        super(itemView);
        commentName = itemView.findViewById(R.id.comment_name_text);
        commentEmail = itemView.findViewById(R.id.comment_email_text);
        commentBody = itemView.findViewById(R.id.comment_body_text);
    }
}