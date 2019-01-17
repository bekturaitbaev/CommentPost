package com.example.post.commentpost.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.post.commentpost.Model.CommentAction;
import com.example.post.commentpost.Model.CommentModel;
import com.example.post.commentpost.Model.PostModel;
import com.example.post.commentpost.View.CommentActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CommentPresenter {
    private static CommentActivity commentActivity;
    private ArrayList<CommentModel> commentList = new ArrayList<>();
    private Context mContext;
    private static String postId;
    public CommentPresenter(){

    }

    public void postClicked(String postid, Context mContext, Activity activity){
        postId = postid;
        this.mContext = mContext;
        Intent intent = new Intent(mContext, CommentActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
    }

    public void getComments(CommentActivity activityComment){
        commentActivity = activityComment;
        CommentAction commentAction = new CommentAction(postId);
        commentAction.execute();
    }

    public void workWithJsonString(String jsonString){
        try {
            JSONArray jsonPostArray = new JSONArray(jsonString);
            for(int i=0; i<jsonPostArray.length(); i++){
                JSONObject jsonPostObject = (JSONObject) jsonPostArray.get(i);
                commentList.add(new CommentModel(jsonPostObject.get("postId").toString(),
                        jsonPostObject.get("id").toString(),
                        jsonPostObject.get("name").toString(),
                        jsonPostObject.get("email").toString(),
                        jsonPostObject.get("body").toString()));
            }
            commentActivity.showCommentList(commentList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
